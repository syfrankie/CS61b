package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.*;


import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private List<Point> points;
    private Map<Point, Node> p2n;
    private TrieSet61B trie;
    private Map<String, List<Node>> n2n;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        points = new ArrayList<>();
        p2n = new HashMap<>();
        trie = new MyTrieSet();
        n2n = new HashMap<>();

        for (Node n: nodes) {
            if (n.name() != null) {
                String temp = cleanString(n.name());
                if (!n2n.containsKey(temp)) {
                    n2n.put(temp, new LinkedList<>());
                }
                n2n.get(temp).add(n);
                trie.add(temp);
            }

            if (neighbors(n.id()).size() != 0) {
                Point p = new Point(n.lon(), n.lat());
                points.add(p);
                p2n.put(p, n);
            }
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        KDTree kdt = new KDTree(points);
        Point ans = kdt.nearest(lon, lat);
        return p2n.get(ans).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> temp = trie.keysWithPrefix(prefix);
        List<String> out = new LinkedList<>();
        for (String t: temp) {
            List<Node> nodes = n2n.get(t);
            for (Node n: nodes) {
                if (!out.contains(n.name())) {
                    out.add(n.name());
                }
            }
        }
        return out;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String temp = cleanString(locationName);
        List<Map<String, Object>> out = new LinkedList<>();
        if (n2n.containsKey(temp)) {
            List<Node> nodes = n2n.get(temp);
            for (Node n: nodes) {
                Map<String, Object> dict = new HashMap<>();
                dict.put("id", n.id());
                dict.put("lat", n.lat());
                dict.put("lon", n.lon());
                dict.put("name", n.name());
                out.add(dict);
            }
        }
        return out;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}

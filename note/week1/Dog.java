public class Dog{
	public int weight;
	public Dog(int w){
		weight = w;
	}
	public void makeNoise(){
		if (weight < 10) {
			System.out.println("sss");
		} else if (weight < 30) {
			System.out.println("mmm");
		} else {
			System.out.println("lll");
		}
	}
	public static Dog comparison(Dog d1, Dog d2){
		if (d1.weight > d2.weight) {
			return d1;
		} else {
			return d2;
		}
	}
}

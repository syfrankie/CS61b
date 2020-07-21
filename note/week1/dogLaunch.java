public class dogLaunch{
	public static void main(String[] args){
		Dog small = new Dog(5);
		Dog medium = new Dog(25);
		Dog large = new Dog(55);
		Dog[] group = new Dog[3];
		group[0] = small;
		group[1] = large;
		group[2] = new Dog(35);
		int i = 0;
		while (i < group.length){
			Dog.comparison(group[i], medium).makeNoise();
			i = i + 1;
		}
	}
}
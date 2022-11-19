package model;

import java.util.Comparator;
import java.util.Date;

public class SortPopular implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
	
		if (o1.getPopular() == o2.getPopular())
			return 0;
		else if (o1.getPopular() < o2.getPopular())
			return 1;
		else
			return -1;
	}
	}



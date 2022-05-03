package com.example.trading.model.goods;

import java.util.List;

public interface ProductDAO {
	List<ProductDTO> list();
	ProductDTO detail(int product_code);
	void update(ProductDTO dto);
	void delete(int product_code);
	void delete_cart(int product_code);
	void insert(ProductDTO dto);
	String file_info(int product_code);
}

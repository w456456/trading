package com.example.trading.model.goods;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository //dao bean
public class ProductDAOImpl implements ProductDAO {
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ProductDTO> list() {
		return sqlSession.selectList("product.list");
	}

	@Override
	public ProductDTO detail(int product_code) {
		return sqlSession.selectOne("product.detail",product_code);
	}

	@Override
	public void update(ProductDTO dto) {
		sqlSession.update("product.update",dto);
	}

	@Override
	public void delete(int product_code) {
		sqlSession.delete("product.delete",product_code);
	}
	
	@Override
	public void delete_cart(int product_code) {
		sqlSession.delete("cart.delete_product",product_code);
	}

	@Override
	public void insert(ProductDTO dto) {
		sqlSession.insert("product.insert",dto);
	}

	@Override
	public String file_info(int product_code) {
		return sqlSession.selectOne("product.file_info",product_code);
	}
}

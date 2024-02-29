package sapa.prevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapa.prevent.data.models.Category;
import sapa.prevent.data.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements  CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void addCategory(Category category) {
        Category category1 = new Category();
        category1.setNameOfCategory(category.getNameOfCategory());
        categoryRepository.save(category1);
    }
}

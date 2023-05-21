package com.example.lab3nguyenhongthai.repository;

import com.example.lab3nguyenhongthai.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}

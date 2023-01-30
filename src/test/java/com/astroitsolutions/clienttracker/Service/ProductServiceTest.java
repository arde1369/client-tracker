package com.astroitsolutions.clienttracker.Service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.clienttracker.Repository.ProductRepository;
import com.astroitsolutions.clienttracker.Utils.TestUtils;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Autowired
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    TestUtils clientTestUtils = new TestUtils();
}

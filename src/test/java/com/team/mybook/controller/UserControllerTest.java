package com.team.mybook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.mybook.data.entity.User;
import com.team.mybook.data.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_get_all_users_success() throws Exception {
        List<User> users = Arrays.asList(
                new User("userTest1", "passwordTest", "mailTest", "male", 20),
                new User("userTest2", "passwordTest", "mailTest", "male", 20));
        when(userRepository.findAll()).thenReturn(users);
        this.mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("userTest1")))
                .andExpect(jsonPath("$[0].password", is("passwordTest")))
                .andExpect(jsonPath("$[0].email", is("mailTest")))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[0].age", is(20)))

                .andExpect(jsonPath("$[1].name", is("userTest2")))
                .andExpect(jsonPath("$[1].password", is("passwordTest")))
                .andExpect(jsonPath("$[1].email", is("mailTest")))
                .andExpect(jsonPath("$[1].gender", is("male")))
                .andExpect(jsonPath("$[1].age", is(20)));

        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void test_get_all_users_not_found() throws Exception {
        this.mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_get_by_name_success() throws Exception {
        User user = new User("userTest1", "passwordTest", "mailTest", "male", 20);

        when(userRepository.findByName("userTest1")).thenReturn(user);

        mockMvc.perform(get("/api/user/{name}", "userTest1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is("userTest1")))
                .andExpect(jsonPath("$.password", is("passwordTest")))
                .andExpect(jsonPath("$.email", is("mailTest")))
                .andExpect(jsonPath("$.gender", is("male")))
                .andExpect(jsonPath("$.age", is(20)));

        verify(userRepository, times(1)).findByName("userTest1");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void test_get_by_name_null() throws Exception {
        when(userRepository.findByName("userTest1")).thenReturn(null);

        mockMvc.perform(get("/api/user/{name}", "userTest1"))
                .andExpect(status().isOk());
        verify(userRepository, times(1)).findByName("userTest1");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void test_create_and_delete_user_success() throws Exception {
        User user = new User("userTest1", "passwordTest", "mailTest", "male", 20);

        when(userRepository.save(user)).thenReturn(user);
        doNothing().when(userRepository).deleteUserByName(user.getName());

        mockMvc.perform(
                post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated());
        mockMvc.perform(
                delete("/api/user/delete/{userName}", user.getName()))
                .andExpect(status().isOk());
    }


    @Test
    public void test_delete_book_fail_not_found() throws Exception {
        User user = new User("userTest1", "passwordTest", "mailTest", "male", 20);

        when(userRepository.findByName(user.getName())).thenReturn(null);

        mockMvc.perform(
                delete("/api/user/delete/{name}", user.getName()))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

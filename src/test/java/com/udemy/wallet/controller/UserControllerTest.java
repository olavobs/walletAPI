package com.udemy.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.wallet.dto.UserDto;
import com.udemy.wallet.entity.User;
import com.udemy.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerTest {

	private static final Long ID = 1L;
	private static final String EMAIL = "email@teste.com";
	private static final String NAME = "User Test";
	private static final String PASSWORD = "1234567";
	private static final String URL = "/user";
	@MockBean
	UserService service;

	@Autowired
	MockMvc mvc;

	@Test
	public void testSaveUser() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());

		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID, EMAIL, NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.email").value(EMAIL))
				.andExpect(jsonPath("$.data.name").value(NAME));
	}

	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID, "email", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]")
				.value("Email inválido"));
	}
	
	public User getMockUser() {
		User u = new User();
		u.setName(NAME);
		u.setPassword(PASSWORD);
		u.setEmail(EMAIL);
		u.setId(ID);

		return u;
	}

	public String getJsonPayload(Long id, String email, String name, String password) throws JsonProcessingException {
		UserDto dto = new UserDto();

		dto.setName(name);
		dto.setPassword(email);
		dto.setEmail(email);
		dto.setId(id);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}

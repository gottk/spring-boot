package com.au.silverrail.service;

import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.silverrail.domain.Attachment;
import com.au.silverrail.domain.User;
import com.au.silverrail.exception.CharacterLimitException;
import com.au.silverrail.exception.InvalidAttachmentException;
import com.au.silverrail.exception.InvalidCharacterException;
import com.au.silverrail.repository.UserRepository;
import com.au.silverrail.utils.RegexUtils;

@Component
public class UserServiceImpl implements UserService {

	private static final int CHARACTER_LIMIT = 200;

	@Autowired
	private UserRepository userRepository;

	public String getState(int id) {
		return userRepository.getUserById(id).getState();
	}

	public int getSum(int id) {
		int sum = 0;

		User user = userRepository.getUserById(id);		
		Matcher matcher = RegexUtils.CreateMatcher("\\d+", user.getState());

		while(matcher.find()) {
			sum += Integer.parseInt(matcher.group());
		}

		return sum;
	}

	@Override
	public String getChars(int id) {
		User user = userRepository.getUserById(id);		
		Matcher matcher = RegexUtils.CreateMatcher("\\D+", user.getState());
		StringBuilder builder = new StringBuilder();

		while(matcher.find()) {
			builder.append(matcher.group());
		}

		return builder.toString();
	}

	@Override
	public void addCharacters(int id, Attachment attachment) throws InvalidAttachmentException, CharacterLimitException {
		
		if(!Validate(attachment))
			throw new InvalidAttachmentException();
		
		User user = userRepository.getUserById(id);		
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < attachment.getAmount(); i++) {
			builder.append(attachment.getCharacter());
		}

		builder.append(user.getState());
		String state = builder.toString();

		if(state.length() > CHARACTER_LIMIT) {
			throw new CharacterLimitException();
		} else {
			user.setState(builder.toString());		
			userRepository.updateUser(user);
		}
	}

	@Override
	public void deleteCharacter(int id, String character) throws InvalidCharacterException {
		User user = userRepository.getUserById(id);

		if(character.length() != 1)
			throw new InvalidCharacterException();

		String state = user.getState();
		int index = state.lastIndexOf(character);

		if(index != -1) {

		}
	}

	private static boolean Validate(Attachment attachment) {
		return attachment.getCharacter().length() == 1
				&& attachment.getAmount() > 0
				&& attachment.getAmount() < 10;
	}
}
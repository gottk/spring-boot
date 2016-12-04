package com.au.silverrail.service;

import com.au.silverrail.domain.Attachment;
import com.au.silverrail.exception.CharacterLimitException;
import com.au.silverrail.exception.InvalidAttachmentException;
import com.au.silverrail.exception.InvalidCharacterException;

public interface UserService {
	String getState(int id);	
	int getSum(int id);	
	String getChars(int id);
	void addCharacters(int id, Attachment attachment) throws InvalidAttachmentException, CharacterLimitException;
	void deleteCharacter(int id, String character) throws InvalidCharacterException;
}
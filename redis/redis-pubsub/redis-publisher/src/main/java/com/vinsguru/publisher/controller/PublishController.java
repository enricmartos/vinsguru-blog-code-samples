package com.vinsguru.publisher.controller;

import com.vinsguru.dto.Message;
import com.vinsguru.publisher.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublishController.class);

	@Autowired
	private PublisherService publisherService;

	@GetMapping(value = {"/publish/{chatMessage}/{userId}"}, produces = MediaType.TEXT_PLAIN_VALUE)
	public void publish(@PathVariable final String chatMessage, @PathVariable final String userId) {
		LOGGER.info(">> publish() chatMessage {} userId {}", chatMessage, userId);

		Message message = new Message();
		message.setText(chatMessage);

		publisherService.publishMessage(message, userId);

		LOGGER.info("<< publish()");
	}
}

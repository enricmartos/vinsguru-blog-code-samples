/**
 * Copyright (c) 2021 masvoz
 *
 * Author: enric.martos@masvoz.es
 */
package com.vinsguru.subscriber.controller;

import com.vinsguru.subscriber.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectController.class);

	@Autowired
	private SubscriberService subscriberService;

	@GetMapping(value = {"/connect/{userId}"}, produces = MediaType.TEXT_PLAIN_VALUE)
	public void connect(@PathVariable final String userId) {
		LOGGER.info(">> connect() userId {}", userId);

		subscriberService.subscribeTo(userId);

		LOGGER.info("<< connect()");
	}
}

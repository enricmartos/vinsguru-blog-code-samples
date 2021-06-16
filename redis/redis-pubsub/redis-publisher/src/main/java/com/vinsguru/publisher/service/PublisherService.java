package com.vinsguru.publisher.service;

import com.vinsguru.dto.Message;

public interface PublisherService {

	void publishMessage(Message message, String userId);
}

package com.ingenieriahuemul.flamenco.app.service;

import org.junit.Test;

public class TestFCM {

	@Test
	public void sendMessagesPush() {
		// Just I am passed dummy information

		String tokenId = "AAAA1sqJg6w:APA91bHf5Hv6jJ9BIumwlexfTmjFOYTreZ7eLWBpGehb66rPCqYr6glG8HFiGinRyA7SCJZLS0zwZwuwroAT2AivH0Mfkb_Ig2rI94uYQF8wRa10DPVaCXIAp5SZGI7BfZWgsspF1_K7";

		String server_key = "AIzaSyBrChp9o6WOiLw4QkjDGmUEqIpVInufcVg";

		String message = "Welcome to FCM Server push notification!.";

		// Method to send Push Notification

		FCM.send_FCM_Notification(tokenId, server_key, message);

	}
}

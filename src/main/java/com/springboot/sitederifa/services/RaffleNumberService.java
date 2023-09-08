package com.springboot.sitederifa.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.springboot.sitederifa.entities.Client;
import com.springboot.sitederifa.entities.OrderItem;
import com.springboot.sitederifa.services.exceptions.ExceededRaffleLimitException;

@Service
public class RaffleNumberService {

	private Map<Long, Set<Integer>> raffleNumbersMap = new HashMap<>();

	public Set<Integer> generateNumbers(Long raffleId, Client client, int quantity, int maxNumber) {
		Set<Integer> existingNumbers = raffleNumbersMap.getOrDefault(raffleId, new HashSet<>());

		if (existingNumbers.size() + quantity > maxNumber) {
			throw new ExceededRaffleLimitException("Exceeded raffle limit for order: " + client.getId());
		}

		Set<Integer> generatedNumbers = new HashSet<>();
		while (generatedNumbers.size() < quantity) {
			int randomNumber = ThreadLocalRandom.current().nextInt(1, maxNumber + 1);

			if (!existingNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
				existingNumbers.add(randomNumber);
			}
		}

		raffleNumbersMap.put(raffleId, existingNumbers);
		return generatedNumbers;
	}

	public void removeGeneratedNumbers(Long raffleId, Set<Integer> generatedNumbers) {
		Set<Integer> existingNumbers = raffleNumbersMap.getOrDefault(raffleId, new HashSet<>());
		existingNumbers.removeAll(generatedNumbers);
		raffleNumbersMap.put(raffleId, existingNumbers);
	}

	public void deleteRaffleNumbers(OrderItem item) {
		removeGeneratedNumbers(item.getRaffle().getId(), item.getGeneratedNumbers());
	}
}
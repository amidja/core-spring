/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.samples.cafe.xml;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.integration.samples.cafe.Drink;
import org.springframework.integration.samples.cafe.OrderItem;
import org.apache.log4j.Logger;

/**
 * @author Mark Fisher
 * @author Marius Bogoevici
 * @author Tom McCuch
 */

public class Barista {
	private static Logger logger = Logger.getLogger(Barista.class);
	private long hotDrinkDelay = 500;

	private long coldDrinkDelay = 100;

	private AtomicInteger hotDrinkCounter = new AtomicInteger();

	private AtomicInteger coldDrinkCounter = new AtomicInteger();


	public void setHotDrinkDelay(long hotDrinkDelay) {
		this.hotDrinkDelay = hotDrinkDelay;
	}

	public void setColdDrinkDelay(long coldDrinkDelay) {
		this.coldDrinkDelay = coldDrinkDelay;
	}

	public Drink prepareHotDrink(OrderItem orderItem) {
		try {
			Thread.sleep(this.hotDrinkDelay);
			logger.info(Thread.currentThread().getName()
					+ " prepared hot drink #" + hotDrinkCounter.incrementAndGet() + " for order #"
					+ orderItem.getOrderNumber() + ": " + orderItem);
			return new Drink(orderItem.getOrderNumber(), orderItem.getDrinkType(), orderItem.isIced(),
					orderItem.getShots());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		}
	}

	public Drink prepareColdDrink(OrderItem orderItem) {
		try {
			Thread.sleep(this.coldDrinkDelay);
			logger.info(Thread.currentThread().getName()
					+ " prepared cold drink #" + coldDrinkCounter.incrementAndGet() + " for order #"
					+ orderItem.getOrderNumber() + ": " + orderItem);
			return new Drink(orderItem.getOrderNumber(), orderItem.getDrinkType(), orderItem.isIced(),
					orderItem.getShots());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return null;
		}
	}

}

package com.flight.search.engine.service.implementation;

import com.flight.search.engine.dao.FlightDAO;
import com.flight.search.engine.model.FlightMini;
import com.flight.search.engine.model.User;
import com.flight.search.engine.repository.UserRepository;
import com.flight.search.engine.service.CartService;
import com.flight.search.engine.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final FlightService flightService;

    public CartServiceImpl(UserRepository userRepository, FlightService flightService) {
        this.userRepository = userRepository;
        this.flightService = flightService;
    }


    @Override
    public void addToCart(Long flightId, User user) {
        Set<FlightMini> flightMiniSet =  user.getFlightsIds();
        FlightMini flightMini = new FlightMini();
        flightMini.setIdFromApi(flightId);
        flightMiniSet.add(flightMini);
        user.setFlightsIds(flightMiniSet);
        userRepository.save(user);
    }

    @Override
    public List<FlightDAO> getCart(User user) {
        List<FlightDAO> flights = new ArrayList<>();
        for(FlightMini flightMini: user.getFlightsIds()){
            flights.add(flightService.getFlight(String.valueOf(flightMini.getIdFromApi())));
        }
        return flights;
    }

    @Override
    public double sumPrices(User user) {
        List<FlightDAO> flights = getCart(user);
        double sum = 0;
        for(FlightDAO flight: flights){
            sum += flight.getPrice();
        }
        return sum;
    }
}

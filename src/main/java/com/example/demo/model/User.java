package com.example.demo.model;

public record User(
        Integer id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) { }

record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) { }

record Geo(
        String lat,
        String lng
) { }

record Company(
        String name,
        String catchPhrase,
        String bs
) { }
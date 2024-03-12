package com.example.demo.entity;

/**
 * DTO for user, used with for API interaction.
 */
public record UserDto(
        Integer id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {
    record Address(
            String street,
            String suite,
            String city,
            String zipcode,
            Geo geo
    ) {
    }

    record Geo(
            String lat,
            String lng
    ) {
    }

    record Company(
            String name,
            String catchPhrase,
            String bs
    ) {
    }
}


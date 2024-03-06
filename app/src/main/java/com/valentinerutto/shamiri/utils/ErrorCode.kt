package com.valentinerutto.shamiri.utils

enum class ErrorCodes(val message: String) {
    API_FETCH_TIMEOUT("It's taking longer than expected."),
    NETWORK_SEARCH_ERROR("Couldn't complete this search; is your Internet connected?"),
    NETWORK_TIMEOUT("Couldn't get the results ; is your Internet connected?"),
    API_ERROR("There was a problem getting locations or characters."),
    NOT_FOUND("Can't find locations or characters.")
}
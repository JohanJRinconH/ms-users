package com.johanrincon.ms.users.dtos;

public record MessageDTO<T> (int status, T message){}
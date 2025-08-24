package org.giftilist.backend;

import java.time.Instant;
import java.util.Map;

public record ApiError (
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors
) {}


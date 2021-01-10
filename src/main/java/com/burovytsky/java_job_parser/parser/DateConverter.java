package com.burovytsky.java_job_parser.parser;

import java.time.LocalDateTime;

interface DateConverter {
    LocalDateTime formatDate(String date);
}

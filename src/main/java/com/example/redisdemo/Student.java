package com.example.redisdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 I use Lombok in this example to eliminate the boilerplate code for constructors and so-called "data class" methods ( accessors/mutators, equals(), toString(), & hashCode()).
 */
public class Student {
    private String name;
    private String id;
}

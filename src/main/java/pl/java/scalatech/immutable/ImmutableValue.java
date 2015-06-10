package pl.java.scalatech.immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;
import lombok.experimental.Wither;
import lombok.extern.slf4j.Slf4j;

import com.google.common.collect.ImmutableList;

@AllArgsConstructor
@Wither
@Slf4j
@ToString
@Builder
public class ImmutableValue {
    @Getter
    private final int age;
    @Getter
    private final String name;
    @Getter
    private ImmutableList<String> addresses;

}

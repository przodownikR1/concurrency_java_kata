package pl.java.scalatech.immutable;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImmutableItem {
    private String name;
    @Getter
    private final @NonNull int value;
    
}

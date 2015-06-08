package pl.java.scalatech.immutable;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Slf4j
public class ImmutableTest {

    @Test
    public void shouldImmutableObjectCreate() {
        val person = new ImmutableValue(3, "Kalina", ImmutableList.of("Warsow"));
        val olderPerson = person.withAge(person.getAge() + 1);
        log.info("+++ older : {} ,  orginal :  {}", olderPerson, person);
        val builderPerson = ImmutableValue.builder().age(35).name("slawek").addresses(ImmutableList.of("Ilza")).build();
        Assert.assertTrue(builderPerson.getAddresses().contains("Ilza"));
        val persons = Lists.newArrayList(builderPerson, person, olderPerson);
        persons.stream().forEach(p -> log.info("{}", p));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldImmutableAddException() {
        val person = new ImmutableValue(3, "Kalina", ImmutableList.of("Warsow"));
        person.getAddresses().add("Radom");
    }
}

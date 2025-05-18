<style>
  .special{
    color: #aabbff;
    font-weight:bold;
  }
  .text-violet{
    color: #aabbff;
  }
</style>

# Getting Started

<span class="text-violet">Violet</span>
<span class="special">Special</span>


## Properties

### auto.offset.reset:

In `application.properties` we used  `spring.kafka.consumer.auto-offset-reset=latest`.


- latest: The consumer will start reading only new messages that arrive after it starts (this is often the default).
- earliest: The consumer will start reading from the very beginning of the log for that partition – consuming all available messages that haven't been deleted by the retention policy.
- none: If no previous offset is found, an exception will be thrown to the consumer.
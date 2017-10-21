# evol sim
[Desktop/Android App]
Simulating the evolution of virtual populations. Neural networks handle machine learning for each individual, genetic algorithms handle evolution for the whole species.

Multiple populations of virtual entities share the same virtual biome and interact with each other and their environment.
They can perceive events surrounding them via sensors. The data captured by their sensors is then processed by multiple neural networks, representing their brain, and finally sent to motors, which control interactions with their environment, such as moving.
The weights of all the neural networks are packaged into bundles, called DNA, after death and are paired with other bundles of weights. This process is coordinated by genetic algorithms to ensure an increase of the total fitness of each species.
Over multiple generations, the species evolve and hopefully their fitness increases.
Users can influence the simulation by equiping the species with several sensors, brains or motors, as well as editing the configuration of the genetic algorithms to change chances of mutation or the way DNA is paired.

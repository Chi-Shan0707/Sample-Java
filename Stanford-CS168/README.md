# Basic Info

[website](https://web.stanford.edu/class/cs168/)

I focus on the algorithms portion of Stanford-CS168.

l17.pdf

## Important notes

The multiplicative weights update (MW) strategy we see in this lecture has been
rediscovered many times across computer science and operations research.

Although MW is motivated by online decision making, its applications go far beyond
that setting. Many problems can be solved via an iterative approach where the
solution is progressively improved by up-weighting desiderata using MW and updating
the solution accordingly. The connection between the "Learning with Experts" game
and these applications is sometimes indirect, since the objects MW is applied to may
not look like "experts".

### Applications

1. **Game Theory** — MW was used to find Nash equilibria of two-player zero-sum
	games. Represent the game by an m × n payoff matrix; the row (resp. column)
	player chooses a distribution over the m (resp. n) strategies. MW is applied
	alternately by the two players to update their distributions.

2. **Learning Theory** — The most well-known use of MW in learning theory is
	AdaBoost, which builds a strong binary classifier from many weak classifiers.
	MW was also used to design the Winnow algorithm for linear classification. In
	both cases the classifier is learned iteratively: misclassified samples are
	up-weighted and the classifier is updated to better fit the reweighted data.

3. **Optimization** — MW can solve various convex optimization problems,
	including linear programs (see Lecture #18). MW tracks a distribution over
	constraints and up-weights violated constraints.

4. **Bandits** — Variants of MW (e.g., Exp3) work in bandit feedback settings,
	where you only observe the loss of the chosen arm rather than losses of all
	experts each round.

lec18.pdf


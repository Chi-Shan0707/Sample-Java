# Online Algorithm

## Online Decision Making
<P>
What is online decision making?<br> Suppose we have a decision that we’re trying to make, and we have n experts thathave conflicting opinions. Who do we trust, and which decision should we make? Here, in each successive trial, weknow the history up until the current trial (that is, we know nothing in trial 1, but in trial 2, we know the results fromtrial 1, etc.).
<p>

***

### Experts problems 

***

#### Example
<p>
Suppose we have to make a decision on whether the weather will be rainy on a given day. We have n experts, and each day we keep track of which experts were wrong, and keep a running “loss tally” foreach expert (where 0 is a correct prediction and 1 is an incorrect prediction):
<p>

|expert id| day1 loss tally| day2 loss tally|
|------|----------------|----------------|
|1| 1  ， 1 | 1  ， 2 |
|2| 1  ， 1 | 0  ， 1 |
|3| 0  ， 0 | 1  ， 1 |
|4| 0  ， 0 | 0  ， 0 |
|5| 1  ， 1 | 1  ， 2 |

<P>
Here, each day, we want to make an “online” decision; that is, we only know the results from previous daysand the loss tallies up until now. After each day, the losses are revealed and we update the tallies.
<P>

***

#### Definition
<br>

We have experts $E_1,...,E_n$  and days  $1,...,T$
<br>
On each day $t \in \{1,...,n\}$<br>
1. Each expert makes a prediction.
2. We choose an expert $E_{id[t]}$ to follow(choose an answer from their predictions).
3. Then the real outcome is revealed, and $E_j$ 's prediction incurs loss $l_{j,t}$.


Ideally, we want to compare   $ L = \sum_{t=1}^T \ l_{id[t],t}$ and $\sum_{t=1}^T \min_{id=1}^{n} \{ l_{id,t} \}$  ;  the latter case is if we magically know.<br>
This is not a realistic goal. Going back to the rain example, suppose we have two experts, where one expert always predicts that it does not rain, and one expert always predicts that it does rain. In the optimal case, we magically know whether it rains or not, and our loss is 0. However, more realistically, if it rains 50% of the time, no matter what we do, we incur a loss of approximately $\frac{T}{2}$; we expect to be wrong half of the time, as we can't possibly predict a uniformly random outcome.

As such, we will settle for something slightly weaker, i.e. compare our $$L$$ to $$\min_{id} \sum_{t=1}^T \ l_{id,t}$$. That is, we compare our actual loss to the case where we look back and only follow one expert throughout. We further define

$$R := L - \min_{id} \sum_{t=1}^T \ l_{id,t} = L - L^*$$
<br>

- There could be multiple actions that we could take (not just binary as we have in the previous example).<br>
- The losses can be some number $l_{j,t} \in [0,1] $. Here, all that is needed is that the loss is bounded; we can just rescale to [0,1] anyways.<br>
- We don't assume that past prediction-performances predict future performance.<br>
- Maybe each expert is wrong.
- In the traditional mode, you can only follow one expert on each day.
- The choice can be discrete, like "Yes/No", or can be continuous, like $f(x)\in C_[0,1]$

#### Strategies and Algorithms

1. Follow a fixed expert for all $T$ rounds — trivial bound: $R \le T$ (each round's loss is at most 1).

2. Randomly choose an expert to follow each round — the expected loss equals the average expert loss; this provides no strong worst-case regret guarantee.

3. If there exists an expert who is always correct, use the elimination/majority approach: at each round pick the option favored by the majority of experts who have not yet made any mistake (eliminate experts when they err). So $R ≤ log_2(n)$

4. If there exists an expert with at most $M$ mistakes overall, run an elimination or halving-style algorithm (remove experts when they err) to leverage that guarantee; this yields mistake/regret bounds that scale with $M ·log(n)$


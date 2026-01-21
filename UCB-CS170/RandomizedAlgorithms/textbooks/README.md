# Preface

Although these problems may be hard to solve on some set of pathological inputs, on most inputs (in particular, those that occur in real-life applications) the problem is actually easy to solve.

If we think of the input as being randomly selected according to some probability distribution on the collection of all possible inputs, we are very likely to obtain a problem instance that is easy to solve, and instances that are hard to solve appear with relatively small probability.

# Chapter One: Events and Probability

## 1.1 Application: Verifying Polynomial Identities

**Problem:** Given a polynomial identity:
$$p(x) = a_n x^n + a_{n-1} x^{n-1} + \cdots + a_1 x + a_0 = (x-x_1)(x-x_2) \cdots (x-x_n)$$

**Solution:** Randomly choose $X \in \{1, 2, \ldots, kn\}$. If $p(X)$ equals both sides, then the error probability is at most $\dfrac{1}{k}$.



## 1.2. Axioms of Probability

**Definition 1.1:** A probability space has three components:
1. A sample space $\Omega$, which is the set of all possible outcomes of the random process modeled by the probability space;
2. A family of sets $\mathcal{F}$ representing the allowable events, where each set in $\mathcal{F}$ is a subset of the sample space $\Omega$; and
3. A probability function $P: \mathcal{F} \to \mathbb{R}$ satisfying Definition 1.2.

> An element of $\Omega$ is called a simple or elementary event.<br>
$\mathcal{F} = 2^{\Omega}$

**Definition 1.2:** A probability function is any function $P: \mathcal{F} \to \mathbb{R}$ that satisfies the following conditions:
1. For any event $E$, $0 \leq P(E) \leq 1$;
2. $P(\Omega) = 1$; and
3. For any finite or countably infinite sequence of pairwise mutually disjoint events $E_1, E_2, E_3, \dots$, $P\left( \bigcup_{i=1} E_i \right) = \sum_{i=1} P(E_i)$.

<br>

> $E_1 − E_2$ for the occurrence of an event that is in $E_1$ but not in $E_2$.


For the previous problem, when we repeat sampling without replacement,our bounds on the probability of making an error are actually slightly better without replacement.<br>*Negligible!!!!*

<br>

## 1.3. Application: Verifying Matrix Multiplication

**Problem:** Verify whether matrices $A$ and $B$ multiply to give $C$, i.e., $A \cdot B = C$.

**Solution:** Randomly choose a vector $\bar{r}$ with entries uniformly from $\{0, 1\}$. Compute $A \bar{r}$ and $C \bar{r}$. If $A \bar{r} = C \bar{r}$, accept the multiplication as correct; otherwise, reject. Using the principle of deferred decisions, choose $r_n$ to $r_2$ first, then set $r_1$ according to the equation derived from the verification condition. The error probability is at most $1/2$.<br>

> We can prove this by conditioning on $(r_2,\dots,r_n)$:<br>
> The formula for $r_1$, given $r_2, \dots, r_n$, is:
$$r_1 = -\frac{\sum_{j=2}^n d_{1j} r_j}{d_{11}}$$
> where $d_{ij}$ are elements from the matrix $D = A B - C$.
>$$
\begin{aligned}
\Pr\big(AB\bar r = C\bar r\big)
&= \sum_{(x_2,\dots,x_n)\in\{0,1\}^{n-1}} \Pr\Big((AB\bar r = C\bar r)\cap ((r_2,\dots,r_n)=(x_2,\dots,x_n))\Big) \\
&\le \sum_{(x_2,\dots,x_n)\in\{0,1\}^{n-1}} \Pr\Big(\Big(r_1 = -\frac{\sum_{j=2}^n d_{1j} r_j}{d_{11}}\Big)\cap ((r_2,\dots,r_n)=(x_2,\dots,x_n))\Big) \\
&= \sum_{(x_2,\dots,x_n)\in\{0,1\}^{n-1}} \Pr\Big(r_1 = -\frac{\sum_{j=2}^n d_{1j} r_j}{d_{11}}\Big)\cdot \Pr\big((r_2,\dots,r_n)=(x_2,\dots,x_n)\big) \\
&\le \sum_{(x_2,\dots,x_n)\in\{0,1\}^{n-1}} \tfrac{1}{2}\,\Pr\big((r_2,\dots,r_n)=(x_2,\dots,x_n)\big) \\
&= \tfrac{1}{2}.
\end{aligned}
$$

>**Remark** :<br>
For line 1->line2, the "$\le$" is because line1 implies line2
<br>

The inequality holds because for each fixed $(x_2,\cdots,x_n)$ there is at most one choice of $r_1 \in \{0,1\}$ that satisfies the equality, so the conditional probability is at most $\frac{1}{2}$.

**Principle of Deferred Decisions:** This principle simplifies probability calculations by choosing random variables sequentially and conditioning on earlier choices. In this application:

- Randomly select $r_n$ down to $r_2$ uniformly from {0,1}, fixing the right-hand side of Eq. (1.1).
- Before selecting $r_1$, the equation's right side is determined, leaving at most one correct value for $r_1$.
- Since $r_1$ has two possible choices (0 or 1), the probability that the equation holds is at most 1/2.
- Therefore, the probability that $A\bar{r} = C\bar{r}$ (indicating correct matrix multiplication) is at most 1/2, bounding the error probability.



An interesting related problem is to evaluate the gradual change in our confidence in the correctness of the matrix multiplication as we repeat the randomized test. Toward that end we introduce Bayes' law.

**Theorem 1.7 (Bayes' Law).** Let $E_1, E_2, \dots, E_n$ be mutually disjoint events in the sample space $\Omega$ with $\bigcup_{i=1}^n E_i = \Omega$. For any event $B$ with $P(B)>0$,

$$
P(E_j \mid B) = \frac{P(B \mid E_j)\,P(E_j)}{\displaystyle\sum_{i=1}^n P(B \mid E_i)\,P(E_i)}.
$$


In the matrix multiplication case, if *we have no information about the process* that generated the identity then a reasonable prior assumption is that the identity is correct with probability $1/2$.

Let $E$ denote the event that the matrix identity is correct and let $\bar{E}$ denote the event that it is incorrect. Let $B$ be the event that after $i$ independent randomized tests the verifier still reports ``no error''. With the prior
$$P(E)=P(\bar{E})=\tfrac{1}{2},$$
Bayes' law gives
$$
P(E\mid B)=\frac{P(B\mid E)P(E)}{P(B\mid E)P(E)+P(B\mid\bar{E})P(\bar{E})}.
$$
If $E$ is true then the tests always pass, so $P(B\mid E)=1$. If $\bar{E}$ is true then each independent test passes with probability at most $1/2$, hence
$$P(B\mid\bar{E})\le\bigl(\tfrac{1}{2}\bigr)^i.$$
Therefore
$$
P(E\mid B) \ge \frac{\tfrac{1}{2}}{\tfrac{1}{2}+\tfrac{1}{2}\bigl(\tfrac{1}{2}\bigr)^i} = \frac{1}{1+2^{-i}} = \frac{2^i}{2^i+1} = 1-\frac{1}{2^i+1}.
$$

Hence the posterior probability that the identity is correct after $i$ successful tests is at least $2^i/(2^i+1)$ (so the posterior error probability is at most $1/(2^i+1)$).


## 1.4. Application: Naïve Bayesian Classifier


## 1.5. Application: A Randomized Min-Cut Algorithm



> Remark Union bound



# Chapter Two: Discrete Random Variables and Expectation

## 2.4. The Geometric Distribution
**Definition 2.8**: A geometric random variable $X$ with parameter $p$ is given by the following probability distribution on $n = 1, 2,\cdots$:
$$Pr(X = n) = (1 - p)^{n-1} p.$$

**Lemma 2.8**: For a geometric random variable $X$ with parameter $p$ and for $n > 0$,
$Pr(X = n + k | X > k) = Pr(X = n)$.<br>
This lemma tells us that: the geometric distribution is memoryless.

**Lemma 2.9**: Let $X$ be a discrete random variable that takes on only nonnegative integer values. Then
$$E[X] = \sum_{i=1}^\infty Pr(X \geq i) = \sum_{i=1}^\infty \sum_{j=i}^\infty Pr(X = j).$$
Hence,
$$E[X] = \sum_{i=1}^\infty (1 - p)^{i-1} = \frac{1}{1 - (1 - p)} = \frac{1}{p}.$$



### 2.4.1. Example: Coupon Collector's Problem

**Problem:** In the coupon collector's problem, we have $n$ distinct coupons, and we collect coupons one by one, each time buying a cereal box which always at random contains a type of coupon from the $n$ types. The problem is to find the expected number of cereal boxes needed to buy at least one of each type.

**Solution:** Let $T$ be the number of coupons collected until all $n$ types are obtained. The expected value $E[T]$ can be computed as follows:

The time to collect the first new coupon after having $k$ distinct coupons is geometric with parameter $\frac{n-k}{n}$. Thus,

$$E[T] = \sum_{k=1}^n \frac{n}{n-k+1} = n \sum_{k=1}^n \frac{1}{k} = n H_n \approx n \ln n,$$

where $H_n$ is the $n$ th harmonic number.

# Randomized Algorithms
- **Las Vegas** : Always correct, runtime is a random variable
- **Monte Carlo**: Always runs within a fixed time bound, but correctness random


## Probability recap

- **Expectation (discrete):**

	Inline: $E[X]=\sum_k k\cdot\Pr(X=k)$ — the weighted average of possible values.

- **Linearity of expectation:**

	$E[aX + bY] = aE[X] + bE[Y]$ — holds regardless of whether `X` and `Y` are independent or not.

- **Markov Inequality:**

	For any nonnegative random variable $X$ and any $t>0$,

	$Pr[X > t] \le \dfrac{E[X]}{t}$.

	Proof (sketch): since $X \ge t\cdot 1_{\{X>t\}}$, take expectations to get $E[X]\ge t\Pr[X>t]$.

	Example: if $E[X]=10$ then $\Pr[X>100]\le 0.1$.

---
## Las Vegas

### Expected Runtime of Quick Sort
> Insight<br>
> 1. Runtime is proportional to $\Sigma Comparisons$ 
> 2. Never compare A[i] and A[j] more than once


**Analysis** :<br>
Define
$$
X_{i,j} = \begin{cases}
1, & \text{if the $i$-th smallest element has been compared to the $j$-th smallest},\\
0, & \text{otherwise.}
\end{cases}\quad\text{for } i \lt j.
$$

**Proof.** Let $C$ be the total number of comparisons. Using the indicators $X_{i,j}$ we have
$$
C = \sum_{1\le i<j\le n} X_{i,j},\qquad
E[C] = \sum_{1\le i<j\le n} \Pr(X_{i,j}=1).
$$
First, consider the divide-and-conquer process: the segement we sort will finally hold only one element, which means that **for fixed $i \lt j$, they will finally be separated.**<br> At the certain round that **separates $i,j$,the pivot has to be chosen from the set $\{i,\dots,j\}$**. And notice that **when $i,j$ is compared, then they must become separated.** Together, we can conclude that:<br>
$$
\Pr(X_{i,j}=1)=\frac{2}{j-i+1}.
$$
Therefore
$$
E[C]=\sum_{1\le i<j\le n}\frac{2}{j-i+1}=2\sum_{d=1}^{n-1}\frac{n-d}{d+1}\le 2n\sum_{d=1}^{n-1}\frac{1}{d+1}=2n(H_n-1)\le 2nH_n\le 2n(\ln n+1).
$$
Hence $E[C]=O(n\log n)$ (in particular $E[C]\lesssim 2n\ln n$).


***
<br>

### Expected Runtime of Quick Select
<br>

***

## Monte Carlo

### Freivald's Algorithm: Verifying Matrix-Matrix Mult

> **Desc** :<br>
> Given $A,B,C \in \R^{n \times n} $ <br>
> Question: Does $A \times B = C $

> **Steps** :<br>
> 1. Pick $X_1,\dots,X_t \in \{0,1\}^n $ independently, uniformly at random
> 2. for(const auto &x : X ){ if( $ABx \neq Cx $)return false; }

> **Claim**：<br>
> if $AB \neq C$, then $\Pr(ABx = Cx) \le \frac{1}{2}$

**Proof**:<br>
Define $D:=AB-C,D \neq O$<br>
Hence, $ \exist i,j , s.t. \text{ } D_{i,j} \neq 0 $<br>
Iff $Dx=0$, then let $ \widetilde{x} := x _{|flip(j)}$ and $ D \widetilde{x} \neq 0$


### Karger's Contraction Algorithm — Find Global Min-Cut

> Def:<br>
> Graph $G$ is undirected.<br>
> A cut of a graph is a partition of $V$ into two non-empty sets.<br>
> Size of a cut is the total weight (or number) of edges crossing the cut.<br>
> The subsets are not necessarily connected.<br>

**Algorithm (Karger).** Repeatedly pick a uniformly random edge and contract it (merge the endpoints and remove self-loops) until only two super-nodes remain; the set of edges between them defines a cut.

**Claim.** If the global minimum cut size is $r$, a single run of Karger's algorithm returns that min-cut with probability at least $\dfrac{2}{n(n-1)}$.

**Proof.** At a stage with $k$ vertices remaining, every vertex has degree at least $r$, so the number of edges is at least $k\frac{r}{2}$. Thus the probability that a uniformly random edge crosses the min-cut is at most

$$
\frac{r}{ \#edges } \le \frac{r}{k \frac{r}{2}} = \frac{2}{k}.
$$

Hence the probability we avoid contracting a min-cut edge at this step is at least $1-\tfrac{2}{k}=(k-2)/k$. Multiplying over the contraction steps from $k=n,n-1,\dots,3$ we get
$$
\prod_{k=n}^{3}\frac{k-2}{k}=\frac{2}{n(n-1)}.
$$
Therefore a single run succeeds with probability at least $2/(n(n-1))\ge 2/n^2$.

To boost success probability to $1-\delta$, run the algorithm independently $T$ times; the failure probability is at most \((1-2/(n(n-1)))^T\). Choosing $T=\Theta(n^2\log(1/\delta))$ yields success probability at least $1-\delta$.

***
[Record](https://www.bilibili.com/video/BV1mu1CYRESn?spm_id_from=333.788.videopod.sections&vd_source=de61571668b4f9b7a6cbfb72c2ad3a42&p=22)
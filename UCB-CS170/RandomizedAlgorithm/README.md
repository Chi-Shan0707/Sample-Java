# Randomized Algorithms
- **Las Vegas** : Always correct, runtime is a random variable
- **Monte Carlo**: Always runs within a fixed time bound, but correctness random


## Probability recap

- **Expectation (discrete):**

	Inline: $E[X]=\\sum_k k\\cdot\\Pr(X=k)$ — the weighted average of possible values.

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
For fixed $i<j$, the elements $i$ and $j$ are compared exactly when the first pivot chosen from the set $\{i,\dots,j\}$ is either $i$ or $j$, so
$$
\Pr(X_{i,j}=1)=\frac{2}{j-i+1}.
$$
Therefore
$$
E[C]=\sum_{1\le i<j\le n}\frac{2}{j-i+1}=2\sum_{d=1}^{n-1}\frac{n-d}{d+1}\le 2n\sum_{d=1}^{n-1}\frac{1}{d+1}=2n(H_n-1)\le 2nH_n\le 2n(\ln n+1).
$$
Hence $E[C]=O(n\log n)$ (in particular $E[C]\lesssim 2n\ln n$).

### Expected Runtime of Quick Select

***

## Monte Carlo

### Freivald's Algorithm: Verifying Matrix-Matrix Mult

> **Desc** :<br>
> Given $A,B,C \in \R^{n \times n} $ <br>
> Question: Does $A \times B = C $

> **Steps** :<br>
> 1. Pick $X_1,\dots,X_t \in \{0,1\}^n $
> 2. for(const auto &x : X ){ if( $ABx \neq Cx $)return false; }
***
[Record](https://www.bilibili.com/video/BV1mu1CYRESn?spm_id_from=333.788.videopod.sections&vd_source=de61571668b4f9b7a6cbfb72c2ad3a42&p=22)
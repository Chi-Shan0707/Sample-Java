# Randomized Algorithms
- **Las Vegas** : Always correct, runtime is a random variable
- **Monte Carlo**: Always runs within a fixed time bound, but correctness random


## Probability recap

- **Expectation (discrete):**

	Inline: `$E[X]=\\sum_k k\\cdot\\Pr(X=k)$` — the weighted average of possible values.

- **Linearity of expectation:**

	`$E[aX + bY] = aE[X] + bE[Y]` — holds regardless of whether `X` and `Y` are independent.

- **Markov Inequality:**

	For any nonnegative random variable $X$ and any $t>0$,

	`\Pr[X > t] \le \dfrac{E[X]}{t}`.

	Proof (sketch): since $X \ge t\cdot 1_{\{X>t\}}$, take expectations to get $E[X]\ge t\Pr[X>t]$.

	Example: if $E[X]=10$ then $\Pr[X>100]\le 0.1$.

---

[Record](https://www.bilibili.com/video/BV1mu1CYRESn?spm_id_from=333.788.videopod.sections&vd_source=de61571668b4f9b7a6cbfb72c2ad3a42&p=22)
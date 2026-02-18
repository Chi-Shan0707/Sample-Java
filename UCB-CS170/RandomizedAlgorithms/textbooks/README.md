# Randomized Algorithms Notes

- [中文部分](#中文部分)
- [English version](#english-version)

---

## 中文部分

### 前言

虽然某些问题在一些病态输入上很难求解，但在大多数输入（尤其是实际应用中常见输入）上，这些问题往往是容易求解的。

如果把输入看作按照某个概率分布随机生成，那么我们很可能得到一个容易处理的实例，而困难实例出现的概率相对较小。

---

# 第一章：事件与概率

## 1.1 应用：验证多项式恒等式

**问题：** 给定多项式恒等式
$$
p(x)=a_nx^n+a_{n-1}x^{n-1}+\cdots+a_1x+a_0=(x-x_1)(x-x_2)\cdots(x-x_n).
$$

**解法：** 随机选取 $X\in\{1,2,\ldots,kn\}$。若两边在 $X$ 处取值相同，则误判概率至多为 $\dfrac{1}{k}$。

## 1.2 概率公理

**定义 1.1：** 概率空间由三部分组成：

1. 样本空间 $\Omega$：随机过程所有可能结果的集合；
2. 事件族 $\mathcal{F}$：由 $\Omega$ 的子集构成；
3. 概率函数 $P: \mathcal{F}\to\mathbb{R}$，满足定义 1.2。

> $\Omega$ 中的元素称为基本事件（elementary event）。
>
> 常见情形：$\mathcal{F}=2^{\Omega}$。

**定义 1.2：** 若函数 $P: \mathcal{F}\to\mathbb{R}$ 满足以下条件，则称其为概率函数：

1. 对任意事件 $E$，有 $0\le P(E)\le 1$；
2. $P(\Omega)=1$；
3. 对任意有限或可数个两两不交事件 $E_1,E_2,\dots$，有
   $$
   P\!\left(\bigcup_{i=1}^{\infty}E_i\right)=\sum_{i=1}^{\infty}P(E_i).
   $$

> 记号 $E_1-E_2$ 表示“属于 $E_1$ 但不属于 $E_2$”的事件。

在前述多项式验证问题里，若做“无放回抽样”，误差上界会略优于“有放回抽样”（通常是很小的改进）。

## 1.3 应用：验证矩阵乘法

**问题：** 验证 $A\cdot B=C$ 是否成立。

**解法：** 随机选取向量 $\bar r\in\{0,1\}^n$，比较 $AB\bar r$ 与 $C\bar r$。若相等则接受，否则拒绝。利用“延迟决策原则”可得单次误判概率至多 $1/2$。

设 $D=AB-C$，若 $D\ne 0$，则存在行（不妨设为第一行）满足
$$
d_{11}r_1+\sum_{j=2}^n d_{1j}r_j=0
\quad\Longrightarrow\quad
r_1=-\frac{\sum_{j=2}^n d_{1j}r_j}{d_{11}}.
$$
对固定的 $(r_2,\dots,r_n)$，最多只有一个 $r_1\in\{0,1\}$ 能满足该式，因此条件概率至多为 $1/2$，于是
$$
\Pr(AB\bar r=C\bar r)\le \frac12.
$$

**延迟决策原则（Principle of Deferred Decisions）：**

- 先随机决定 $r_n,\dots,r_2$；
- 再看方程对 $r_1$ 的唯一约束；
- 因 $r_1$ 只有两种选择，满足约束概率至多 $1/2$。

进一步，考虑重复检验后的置信度变化（贝叶斯法则）。

**定理 1.7（Bayes' Law）**：设 $E_1,\dots,E_n$ 两两不交且并为 $\Omega$，则对任意 $P(B)>0$：
$$
P(E_j\mid B)=\frac{P(B\mid E_j)P(E_j)}{\sum_{i=1}^n P(B\mid E_i)P(E_i)}.
$$

在矩阵验证问题中，若先验设为
$$
P(E)=P(\bar E)=\frac12,
$$
其中 $E$ 表示“等式正确”，$B$ 表示“连续 $i$ 次随机检验均未报错”。那么
$$
P(E\mid B)=\frac{P(B\mid E)P(E)}{P(B\mid E)P(E)+P(B\mid\bar E)P(\bar E)}.
$$
因为 $P(B\mid E)=1$，且 $P(B\mid\bar E)\le (1/2)^i$，所以
$$
P(E\mid B)\ge\frac{1}{1+2^{-i}}=\frac{2^i}{2^i+1}=1-\frac{1}{2^i+1}.
$$

即：做了 $i$ 次都通过后，等式正确的后验概率至少为 $\dfrac{2^i}{2^i+1}$。

## 1.4 应用：朴素贝叶斯分类器

（本节待补充）

## 1.5 应用：随机化最小割算法

（本节待补充）

> 备注：并合界（Union Bound）在后续分析中会频繁使用。

---

# 第二章：离散随机变量与期望

## 2.4 几何分布

**定义 2.8：** 参数为 $p$ 的几何随机变量 $X$ 的分布为
$$
\Pr(X=n)=(1-p)^{n-1}p,\quad n=1,2,\dots
$$

**引理 2.8（无记忆性）：** 对 $n>0$、$k\ge 0$，
$$
\Pr(X=n+k\mid X>k)=\Pr(X=n).
$$

**引理 2.9：** 若离散随机变量 $X$ 只取非负整数值，则
$$
E[X]=\sum_{i=1}^{\infty}\Pr(X\ge i)=\sum_{i=1}^{\infty}\sum_{j=i}^{\infty}\Pr(X=j).
$$
因此对几何分布有
$$
E[X]=\sum_{i=1}^{\infty}(1-p)^{i-1}=\frac{1}{p}.
$$

### 2.4.1 例：集券者问题（Coupon Collector）

有 $n$ 类不同优惠券，每次独立等概率抽到一类。记收齐所有类型所需抽取次数为 $T$。

当已收集到 $k$ 种时，再收集到一个“新种类”的等待时间服从参数 $\dfrac{n-k}{n}$ 的几何分布，因此
$$
E[T]=\sum_{k=1}^n\frac{n}{n-k+1}=n\sum_{k=1}^n\frac{1}{k}=nH_n\approx n\ln n.
$$

## 2.5 应用：快速排序的期望运行时间

**定理 2.12：** 若每次都取子数组第一个元素作为枢轴，且输入是所有排列的均匀随机样本，则确定性 Quicksort 的期望比较次数为
$$
2n\ln n+O(n).
$$

核心原因：元素 $y_i,y_j$ 是否比较，只取决于它们在区间 $Y^{ij}$ 中谁先作为枢轴；在随机输入下，每个元素先出现概率相同，故与随机枢轴分析相同，结合期望线性性得到结论。

---

# 第三章：矩与偏差

## 3.3 切比雪夫不等式

**定理 3.6（Chebyshev）**：对任意随机变量 $X$ 与任意 $a>0$，
$$
\Pr\bigl(|X-E[X]|\ge a\bigr)\le\frac{\mathrm{Var}[X]}{a^2}.
$$

证明来自对非负随机变量 $(X-E[X])^2$ 应用 Markov 不等式。

**推论 3.7：** 若 $\sigma=\sqrt{\mathrm{Var}[X]}$，对任意 $t>0$ 有
$$
\Pr\bigl(|X-E[X]|\ge t\sigma\bigr)\le\frac{1}{t^2}.
$$

### 3.3.1 例：用 Chebyshev 分析集券者问题

设 $X=\sum_{i=1}^nX_i$，其中 $X_i\sim\mathrm{Geom}(p_i)$，$p_i=\dfrac{n-i+1}{n}$，且相互独立。

于是
$$
\mathrm{Var}[X]=\sum_{i=1}^n\mathrm{Var}[X_i].
$$

**引理 3.8：** 若 $Y\sim\mathrm{Geom}(p)$，则
$$
\mathrm{Var}[Y]=\frac{1-p}{p^2}.
$$

可通过两种方法推导 $E[Y^2]$：

1. **生成函数法**：由
   $$
   \sum_{i=1}^{\infty}i^2x^i=\frac{x^2+x}{(1-x)^3}
   $$
   代入 $x=1-p$ 得 $E[Y^2]=\dfrac{2-p}{p^2}$；
2. **条件期望法（利用无记忆性）**：同样可得 $E[Y^2]=\dfrac{2-p}{p^2}$。

从而
$$
\mathrm{Var}[X]\le n^2\sum_{j=1}^n\frac{1}{j^2}\le\frac{\pi^2n^2}{6}.
$$

应用 Chebyshev：
$$
\Pr(X\ge 2E[X])
\le\frac{\mathrm{Var}[X]}{E[X]^2}
\le\frac{\pi^2}{6H_n^2}
=O\!\left(\frac{1}{\log^2 n}\right).
$$

> 对比：Markov 只能给常数级上界；Chebyshev 更紧，但仍不如更精细工具。

## 3.4 中位数与均值

**中位数定义：** 任意满足
$$
\Pr(X\le m)\ge\frac12,\qquad \Pr(X\ge m)\ge\frac12
$$
的 $m$ 都是中位数。

### 优化视角

**定理 3.9：** 对有有限期望的随机变量 $X$：

1. $E[X]$ 最小化均方误差 $E[(X-c)^2]$；
2. 中位数 $m$ 最小化绝对误差 $E[|X-c|]$。

### 均值与中位数的距离

**定理 3.10：** 若 $X$ 标准差为 $\sigma$、均值为 $\mu$、中位数为 $m$，则
$$
|\mu-m|\le\sigma.
$$

证明结合 Jensen 不等式和“中位数最小化绝对偏差”性质。

## 3.5 应用：随机化中位数算法

给定全序集合 $S$（大小为 $n$），目标求全局中位数。

### 3.5.1 算法

1. 有放回均匀抽样 $n^{3/4}$ 个元素构成多重集 $R$；
2. 排序 $R$；
3. 取
   $$
   d=R\!\left[\left\lfloor\tfrac12n^{3/4}-\sqrt n\right\rfloor\right],\quad
   u=R\!\left[\left\lceil\tfrac12n^{3/4}+\sqrt n\right\rceil\right];
   $$
4. 扫描 $S$，构造
   $$
   C=\{x\in S\mid d\le x\le u\},
   $$
   并统计 $\ell_d=|\{x<d\}|$、$\ell_u=|\{x>u\}|$；
5. 若 $\ell_d>n/2$ 或 $\ell_u>n/2$，输出 FAIL；若 $|C|>4n^{3/4}$，输出 FAIL；否则排序 $C$ 并返回第
   $$
   \bigl(\lfloor n/2\rfloor-\ell_d+1\bigr)
   $$
   小元素。

### 3.5.2 分析

- **时间复杂度：** 始终为 $O(n)$（主耗时是对 $S$ 的线性扫描）；
- **成功时正确性：** 若未 FAIL，返回值必为真中位数；
- **失败概率：** 定义坏事件 $E_1,E_2,E_3$（下界太高、上界太低、区间过宽），可证
  $$
  \Pr(\mathrm{FAIL})\le O(n^{-1/4}).
  $$

### Las Vegas 与 Monte Carlo

- **Monte Carlo：** 固定时间，允许小概率失败；
- **Las Vegas：** 重复执行直到成功，结果总正确，期望仍为线性时间。

---

# 第五章：球、箱与随机图

## 5.1 例：生日悖论

房间里有 30 人，问“至少两人同生日”更可能，还是“所有人生日都不同”更可能？

在简化模型下：每个人生日独立且均匀分布于 $365$ 天。

“30 人生日互不相同”的概率可写为
$$
\frac{\binom{365}{30}30!}{365^{30}}
=\prod_{j=1}^{29}\left(1-\frac{j}{365}\right).
$$

数值约为 $0.2937$，因此“至少两人同生日”概率约为 $1-0.2937=0.7063$（超过 70%）。

一般地，若有 $m$ 人、$n$ 种生日，则
$$
\Pr(\text{全不同})=\prod_{j=1}^{m-1}\left(1-\frac{j}{n}\right).
$$

当 $m\ll n$ 时，用 $1-x\approx e^{-x}$：
$$
\Pr(\text{全不同})\approx e^{-m(m-1)/(2n)}\approx e^{-m^2/(2n)}.
$$

令该概率约为 $1/2$，得
$$
m\approx\sqrt{2n\ln2}.
$$
当 $n=365$ 时，$m\approx22.49$，与精确阈值 23 很接近。

此外可得直观上下界：

- 当 $k\le\sqrt n$ 时，借并合界可得冲突概率 $\le\dfrac{k(k-1)}{2n}<1/2$；
- 当人数约达 $2\sqrt n$ 时，“全不同”概率已降到常数（如小于 $1/e$）。

## 5.2 球入箱模型（Balls-and-Bins Model）

将 $m$ 个球独立均匀扔入 $n$ 个箱子。常见问题包括：

- 有多少空箱？
- 最大负载（最满箱子里的球数）是多少？
- 出现碰撞（某箱至少 2 球）的概率是多少？

当 $m=n$ 时，平均负载为 1，但最大负载会随 $n$ 增大缓慢上升。

**引理 5.1：** 当 $n$ 个球独立均匀投入 $n$ 个箱子时，对充分大的 $n$，
$$
\Pr\!\left(\max\text{ load } >\frac{3\ln n}{\ln\ln n}\right)\le\frac1n.
$$

**证明思路：**

1. 固定某个箱子，接收至少 $M$ 球的概率上界为
   $$
   \binom{n}{M}\left(\frac1n\right)^M\le\frac1{M!}\le\left(\frac eM\right)^M;
   $$
2. 对全部 $n$ 个箱子再做一次并合界；
3. 取 $M\ge\dfrac{3\ln n}{\ln\ln n}$，整理可得整体概率至多 $1/n$。

### 5.2.2 应用：桶排序（Bucket Sort）

在输入独立均匀分布前提下，Bucket Sort 可达期望线性时间 $O(n)$。

设 $X_j$ 为第 $j$ 个桶中的元素个数，若桶内排序代价最多为 $cX_j^2$，则第二阶段期望时间
$$
E\!\left[\sum_{j=1}^n cX_j^2\right]=c\sum_{j=1}^nE[X_j^2]=cnE[X_1^2].
$$

当 $X_1\sim B(n,1/n)$ 时，
$$
E[X_1^2]=\frac{n(n-1)}{n^2}+1=2-\frac1n<2,
$$
故第二阶段期望代价为 $O(n)$，总期望时间仍为 $O(n)$。

## 5.3 泊松分布

在球入箱模型中，第一号箱为空的概率为
$$
\left(1-\frac1n\right)^m\approx e^{-m/n}.
$$

设指示变量 $X_i=\mathbf{1}[\text{第 }i\text{ 个箱子为空}]$，空箱总数 $X=\sum_{i=1}^nX_i$。则
$$
E[X]=\sum_{i=1}^nE[X_i]=n\left(1-\frac1n\right)^m\approx ne^{-m/n}.
$$
即空箱占比约为 $e^{-m/n}$。

进一步，固定常数 $r$，某箱恰有 $r$ 个球的概率近似为
$$
p_r\approx\frac{e^{-m/n}(m/n)^r}{r!}.
$$

这引出泊松分布：

**定义 5.1：** 参数为 $\mu$ 的泊松随机变量 $X$ 满足
$$
\Pr(X=j)=\frac{e^{-\mu}\mu^j}{j!},\quad j=0,1,2,\dots
$$

---

## English version

### Preface

Although some problems can be difficult on pathological inputs, they are often easy on most inputs that arise in practice. If we model inputs as random draws from a probability distribution, hard instances tend to occur with relatively small probability.

---

# Chapter One: Events and Probability

## 1.1 Application: Verifying Polynomial Identities

Given
$$
p(x)=a_nx^n+a_{n-1}x^{n-1}+\cdots+a_0=(x-x_1)(x-x_2)\cdots(x-x_n),
$$
pick $X\in\{1,2,\ldots,kn\}$ uniformly at random and test equality at $X$. The error probability is at most $1/k$.

## 1.2 Axioms of Probability

A probability space consists of:

1. Sample space $\Omega$;
2. Event family $\mathcal F\subseteq 2^{\Omega}$;
3. Probability function $P:\mathcal F\to\mathbb R$ such that:
   - $0\le P(E)\le 1$,
   - $P(\Omega)=1$,
   - for pairwise disjoint $E_1,E_2,\dots$,
     $$
     P\!\left(\bigcup_iE_i\right)=\sum_iP(E_i).
     $$

Here, $E_1-E_2$ denotes outcomes in $E_1$ but not in $E_2$.

## 1.3 Application: Verifying Matrix Multiplication

To verify $AB=C$, sample $\bar r\in\{0,1\}^n$ uniformly and compare $AB\bar r$ with $C\bar r$. If $AB\ne C$, then with $D=AB-C\ne 0$, conditioning on $(r_2,\ldots,r_n)$ leaves at most one valid value for $r_1$, so
$$
\Pr(AB\bar r=C\bar r)\le\frac12.
$$

Repeating the test and applying Bayes’ law with prior $P(E)=P(\bar E)=1/2$ yields
$$
P(E\mid B)\ge\frac{2^i}{2^i+1}=1-\frac1{2^i+1},
$$
where $B$ means “$i$ independent tests all pass.”

## 1.4 Application: Naïve Bayesian Classifier

(To be completed.)

## 1.5 Application: A Randomized Min-Cut Algorithm

(To be completed.)

> Remark: Union bounds are used frequently in later analyses.

---

# Chapter Two: Discrete Random Variables and Expectation

## 2.4 The Geometric Distribution

For $X\sim\mathrm{Geom}(p)$,
$$
\Pr(X=n)=(1-p)^{n-1}p,\quad n=1,2,\dots
$$
and the memoryless property holds:
$$
\Pr(X=n+k\mid X>k)=\Pr(X=n).
$$

If $X$ takes nonnegative integers,
$$
E[X]=\sum_{i=1}^{\infty}\Pr(X\ge i).
$$
Hence for geometric $X$, $E[X]=1/p$.

### 2.4.1 Example: Coupon Collector

Let $T$ be the number of draws needed to collect all $n$ coupon types. Then
$$
E[T]=n\sum_{k=1}^n\frac1k=nH_n\approx n\ln n.
$$

## 2.5 Application: Expected Runtime of Quicksort

If each subarray chooses its first element as pivot and the input is a uniformly random permutation, deterministic Quicksort makes
$$
2n\ln n+O(n)
$$
comparisons in expectation.

---

# Chapter Three: Moments and Deviations

## 3.3 Chebyshev’s Inequality

For any random variable $X$ and $a>0$,
$$
\Pr(|X-E[X]|\ge a)\le\frac{\mathrm{Var}[X]}{a^2}.
$$

With $\sigma=\sqrt{\mathrm{Var}[X]}$, for any $t>0$:
$$
\Pr(|X-E[X]|\ge t\sigma)\le\frac1{t^2}.
$$

### 3.3.1 Example: Coupon Collector Tail Bound

For $X=\sum_{i=1}^nX_i$ with independent $X_i\sim\mathrm{Geom}(p_i)$,
$$
\mathrm{Var}[X]\le n^2\sum_{j=1}^n\frac1{j^2}\le\frac{\pi^2n^2}{6}.
$$
Therefore,
$$
\Pr(X\ge 2E[X])\le\frac{\pi^2}{6H_n^2}=O\!\left(\frac1{\log^2 n}\right).
$$

## 3.4 Median and Mean

Any $m$ with
$$
\Pr(X\le m)\ge\frac12,\qquad \Pr(X\ge m)\ge\frac12
$$
is a median.

The expectation minimizes MSE, while the median minimizes MAE. If $X$ has mean $\mu$, median $m$, and standard deviation $\sigma$, then
$$
|\mu-m|\le\sigma.
$$

## 3.5 Application: A Randomized Median Algorithm

Sample $n^{3/4}$ elements with replacement, sort the sample, choose pivots $d,u$, filter to $C=\{x\in S:d\le x\le u\}$, and either fail or return the appropriately indexed element in sorted $C$.

- Runtime is always $O(n)$;
- If output is produced, it is correct;
- Failure probability is $O(n^{-1/4})$.

Repeating until success yields a Las Vegas algorithm with expected linear time.

---

# Chapter Five: Balls, Bins, and Random Graphs

## 5.1 Example: The Birthday Paradox

With $m$ people and $n$ possible birthdays, the probability of no collisions is
$$
\Pr(\text{all distinct})=\prod_{j=1}^{m-1}\left(1-\frac{j}{n}\right).
$$

For $m=30,n=365$, this is about $0.2937$, so a shared birthday occurs with probability above $70\%$.

For $m\ll n$,
$$
\Pr(\text{all distinct})\approx e^{-m(m-1)/(2n)}\approx e^{-m^2/(2n)}.
$$
Setting this to $1/2$ gives
$$
m\approx\sqrt{2n\ln2}.
$$
For $n=365$, this is about $22.49$ (close to 23).

## 5.2 Balls-and-Bins Model

Throw $m$ balls independently and uniformly into $n$ bins. Key questions include empty bins, collisions, and maximum load.

For $m=n$, a standard union-bound argument shows that for sufficiently large $n$,
$$
\Pr\!\left(\max\text{ load}>\frac{3\ln n}{\ln\ln n}\right)\le\frac1n.
$$

### 5.2.2 Application: Bucket Sort

Under uniform random inputs, Bucket Sort runs in expected linear time.

If $X_j$ is the number of elements in bucket $j$ and bucket sorting cost is at most $cX_j^2$, then
$$
E\!\left[\sum_{j=1}^n cX_j^2\right]=cnE[X_1^2].
$$
For $X_1\sim B(n,1/n)$,
$$
E[X_1^2]=2-\frac1n<2,
$$
so the second stage is $O(n)$ in expectation.

## 5.3 The Poisson Distribution

In balls-and-bins,
$$
\Pr(\text{a given bin is empty})=\left(1-\frac1n\right)^m\approx e^{-m/n}.
$$
Hence expected empty bins are
$$
E[X]=n\left(1-\frac1n\right)^m\approx ne^{-m/n}.
$$

For fixed $r$, the probability a given bin has exactly $r$ balls is approximately
$$
p_r\approx\frac{e^{-m/n}(m/n)^r}{r!}.
$$

This leads to the Poisson distribution with parameter $\mu$:
$$
\Pr(X=j)=\frac{e^{-\mu}\mu^j}{j!},\quad j=0,1,2,\dots
$$

# Chapter Seven: Markov Chains and Random Walks

## 7.1. Markov Chains: Definitions and Representations
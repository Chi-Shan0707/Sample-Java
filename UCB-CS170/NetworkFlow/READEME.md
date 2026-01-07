
## NetworkFlow

## LP

### General Form with Inequality Constraints
$$
\begin{align*}
\max \quad & C'X \\
\text{s.t.} \quad & AX \le B
\end{align*}
$$
- **Linear Programming (LP) in general inequality form**

### Standard Form with Equality Constraints
$$
\begin{align*}
\max \quad & C'X \\
\text{s.t.} \quad & AX = B \\
& X \ge 0
\end{align*}
$$
-  **LP in standard equality form**


### Mutual Conversion
> by **slack variable** or  **surplus variable**




$$
\begin{align*}
\max \quad & C'X \\
\text{s.t.} \quad & AX \le B
\end{align*}
$$


$$
x_i = x_i^+ - x_i^-, \quad \text{s.t. } \ x_i^+ \ge 0,\ x_i^- \ge 0
$$



$$
s_j = b_j - (\boldsymbol{A}\boldsymbol{X})_j \quad (\text{namely} \ (\boldsymbol{A}\boldsymbol{x})_j + s_j = b_j)
$$

<p>then we have<p>


$$
\begin{align*}
\max \quad & \boldsymbol{c}^\top \left( \begin{array}{c} x_1^+ - x_1^- \\ x_2^+ - x_2^- \end{array} \right) \\
\text{s.t.} \quad & a_{11}x_1^+ - a_{11}x_1^- + a_{12}x_2^+ - a_{12}x_2^- + s_1 = b_1 \\
& a_{21}x_1^+ - a_{21}x_1^- + a_{22}x_2^+ - a_{22}x_2^- + s_2 = b_2 \\
& x_1^+ \ge 0,\ x_1^- \ge 0,\ x_2^+ \ge 0,\ x_2^- \ge 0 \\
& s_1 \ge 0,\ s_2 \ge 0
\end{align*}
$$

<p>namely<p>

$$
\begin{align*}
\max \quad & \left( \boldsymbol{c}, -\boldsymbol{c} \right)^\top \left( \boldsymbol{x}^+, \boldsymbol{x}^- \right) + \boldsymbol{0}^\top \boldsymbol{s} \\
\text{s.t.} \quad & \boldsymbol{A}\left( \boldsymbol{x}^+ - \boldsymbol{x}^- \right) + \boldsymbol{I}\boldsymbol{s} = \boldsymbol{B} \\
& \boldsymbol{x}^+ \ge \boldsymbol{0},\ \boldsymbol{x}^- \ge \boldsymbol{0},\ \boldsymbol{s} \ge \boldsymbol{0}
\end{align*}
$$


- **Slack variables are introduced to convert inequalities to equalities, which makes the LP conform to the standard form.**


$$
\begin{cases}
AX \le B \\
-AX \le -B
\end{cases}
$$


- **Equality constraints can be decomposed into a pair of inequality constraints, thus transforming the standard form back to the general form.**

## 二、 转化与对偶的本质区别（Essential Difference Between Conversion & Duality）
### 1.  对偶问题的定义（Definition of Dual Problem）
对偶问题是针对**某一个原问题（primal problem）** 构造的新LP问题，满足以下核心对应关系：
| 原问题（Primal Problem） | 对偶问题（Dual Problem） |
|--------------------------|--------------------------|
| 目标函数 $\max C'X$ | 目标函数 $\min B'Y$ |
| 约束 $AX \le B$ | 约束 $A'Y \ge C$ |
| 决策变量 $X$ free | 决策变量 $Y \ge 0$ |

- 英文核心特征：**The dual problem swaps the coefficients of the objective function and the right-hand side of constraints, transposes the constraint matrix, and reverses the direction of inequality constraints.**

### 2.  转化 vs 对偶：核心差异
| 维度 | 形式转化（Form Conversion） | 对偶问题（Dual Problem） |
|------|-----------------------------|--------------------------|
| 本质 | 同一问题的不同数学表述 | 两个不同但密切关联的问题 |
| 目标 | 统一LP的求解格式，方便算法计算（如单纯形法） | 从对偶视角分析原问题的最优解、影子价格等 |
| 关系 | 转化前后的问题**完全等价**（最优解、最优值相同） | 原问题与对偶问题**最优值相等**（强对偶定理），但决策变量含义不同 |

## 三、 关键结论总结（Key Conclusion）
1.  你提到的两类LP形式 **可以互相转化**，转化的核心是引入松弛/剩余变量，目的是将问题转化为适合求解的标准型。
2.  这两类形式 **不是对偶关系**，对偶是原问题与另一类结构互逆的LP问题的关系，和形式转化是完全不同的概念。
3.  英文总结：**The two LP forms are mutually convertible via slack/surplus variables, but they are not dual problems. Duality refers to the inverse relationship between a primal LP and its corresponding dual LP, which is distinct from form conversion.**

---

需要我帮你推导**原问题与对偶问题的强对偶定理证明思路**，并结合例子说明对偶问题的实际意义吗？
$\rhd$

max C' X
s.t AX=B, X>=0

### Ford-Fulkerson

### concepts
1. augmenting path
2. residual graph
3. net flow of S +=  f(u->v)-f(v->u), forall u in S and v in T
4. capacity of a cut : c(S,T) +=  upper_limit(u->v) - lower_limit(v->u), forall u in S and v in T, and the lower_limit is usually zero

### lemma
1. s-t flow decomposes into paths and cycles (tot<=m) <br>
> pf by induction on m
- base case: m=1
- inductive step: show m-1 $\rhd$ m
  case 1: val(f)>0 , dfs from s until (i) cycle (ii) stuck ( must be stuck at t because of the conservation of the flow), then substract the minimum one
  case 2: val(f)=0: cycle somewhere and never get stuck
2. val(f)<val(f* ) $\rhd$ f*-f is a valid flow in Gf

3. Any flow f, and any s-t cuts (S,T), Netflow(S)= val(f)
> pf by induction |S|
- base case: |s|=1, namely S={s}
- indcutive step: s-t cuts (S,T) |S|>1; choose **u** belongs to S.<br>
Denote S' =  S \ {u}, and calculate Netflow(S') $\rightarrow$ Netflow(S) <br>
What contributes to the delta? Netflowf(S)? <br>
delta =   + [ u $\longrightarrow$  S' ]+  [ u $\longrightarrow$ T ]- [ S' $\longrightarrow$ u ] - [T $\longrightarrow$ u] = 0  (because of the conservation of the flow at u!)

4. Naturally, any valid flow = val( f ) = Netflow(S) <=  Capacity (S-T),
> When we calculate Netflow, we subtract something from Capactiy.
namely, max_flow <= min_cut<br>
   But, max_flow >= any flow = a certain cut >= min_cut
> We can find the certain s-t cuts when it terminates:  all the vertices that s can reach comprise "S", the rest part is "T", and this is the min-STCUT

### note
> Terminate? No, when irrational numbers!  
But when all edges are integers, it will terminate! -----integer discrete  
O((N+N)*MAX_FLOW),but input flow_capacity needs (log(max_flow)), so not poly

> min ST-CUT = max_flow , which shows strong duality  
when terminate, the graph shows ST-CUT: in the last iteration, s cannot reach t, all the vertices that s can reach comprise "S", the rest part is "T", and this is the min-STCUT

### Karp

### Dinic

### Lee Sidford

> O(m sqrt(n) log u)





# Complexity Calculation

## Basics (models, time and space)

This section gives concise, mathematically precise foundations for algorithmic complexity. We use a standard deterministic RAM model: basic arithmetic and memory access cost $O(1)$.

- Time complexity: number of basic steps as a function of input size $n$.
- Space complexity: extra memory used as a function of $n$ (excluding input unless stated).

## Asymptotic notation (formal definitions)

- Big-O: $f(n)=O(g(n))$ if there exist constants $c>0$ and $n_0$ such that for all $n\ge n_0$, $0\le f(n)\le c\,g(n)$.
- Big-\Omega: $f(n)=\Omega(g(n))$ iff there exist $c>0, n_0$ with $f(n)\ge c\,g(n)$ for all $n\ge n_0$.
- Big-\Theta: $f(n)=\Theta(g(n))$ iff $f(n)=O(g(n))$ and $f(n)=\Omega(g(n))$.

These definitions hide constant factors and lower-order terms; they describe growth rates as $n\to\infty$.

## Common proof techniques

- Direct comparison: show $f(n)\le c\,g(n)$ (or the reverse) by algebraic manipulation.
- Limit method: if $\limsup_{n\to\infty} f(n)/g(n)<\infty$, then $f=O(g)$; if $\liminf_{n\to\infty} f(n)/g(n)>0$, then $f=\Omega(g)$.
- Induction (substitution): assume a bound for smaller sizes and prove it holds for $n$.
- Recurrence solving: use iteration (unrolling), recursion-tree, or Master Theorem (below).

## Master Theorem (divide-and-conquer recurrences)

Consider recurrences of the form

$$
T(n)=a\,T\left(\frac{n}{b}\right)+f(n),
$$

where $a\ge1$, $b>1$, and $f(n)$ is positive. Let $\alpha=\log_b a$.

- Case 1 (polynomially smaller): If $f(n)=O\big(n^{\alpha-\epsilon}\big)$ for some $\epsilon>0$, then  
  $T(n)=\Theta\big(n^{\alpha}\big).$
- Case 2 (same order up to polylog): If $f(n)=\Theta\big(n^{\alpha}\log^k n\big)$ for some $k\ge0$, then  
  $T(n)=\Theta\big(n^{\alpha}\log^{k+1} n\big).$
- Case 3 (polynomially larger): If $f(n)=\Omega\big(n^{\alpha+\epsilon}\big)$ for some $\epsilon>0$, and the regularity condition $a\,f(n/b)\le c\,f(n)$ for some $c<1$ and large $n$ holds, then  
  $T(n)=\Theta\big(f(n)\big).$

Use these cases to get tight bounds for many divide-and-conquer algorithms.

## Example: Mergesort

Recurrence: $T(n)=2T(n/2)+\Theta(n)$. Here $a=2,b=2,\alpha=1$ and $f(n)=\Theta(n)=\Theta(n^{\alpha})$. By Master Case 2 with $k=0$,  
$T(n)=\Theta(n\log n).$

## Amortized analysis (brief)

Amortized bounds apply to sequences of operations. Common methods:

- Aggregate method: bound total cost of $m$ operations, then average: cost per operation $=\frac{\text{total}}{m}$.
- Accounting method: assign "credits" to operations to pay for expensive ones.
- Potential method: define a potential function $\Phi$ on data-structure states; amortized cost of operation = actual cost $+\Delta\Phi$.

## Lower bounds and reductions

- To prove a lower bound, choose a computation model (e.g. comparison model) and show any algorithm in that model requires at least $g(n)$ steps. Example: comparison sorting has a decision-tree lower bound $\Omega(n\log n)$.
- Reductions: show problem A is at least as hard as problem B by transforming instances of B to A.

## Practical notes

- Constant factors, small-$n$ behavior, and machine models matter in practice; asymptotics guide scalability.
- When claiming $\Theta$ bounds, briefly state the constants or where they come from if a formal proof is needed.


<!-- end of Calculation additions -->


# Algorithm

1. Halt
2. Correctness
3. Efficiency

## NetworkFlow

1. augmenting path
2. residual graph

### Ford-Fulkerson

> Terminate? No, when irrational numbers!  
But when all edges are integers, it will terminate! -----integer discrete  
O((N+N)*MAX_FLOW),but input flow_capacity needs (log(max_flow)), so not poly

> min ST-CUT = max_flow , which shows strong duality  
when terminate, the graph shows ST-CUT: in the last iteration, s cannot reach t, all the vertices that s can reach comprise "S", the rest part is "T", and this is the min-STCUT

### Karp

### Dinic

### Lee Sidford

> O(m sqrt(n) log u)
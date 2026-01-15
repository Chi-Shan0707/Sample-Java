# Streaming Algorithms

- algorithm must process a "stream" of incoming data, then be able to answer some query<br> (Goal:use very lttle memory)
- Dynamic data structure 
- Don't remember all info
- Monte Carlo Algorithms

## A Very Simple Example
> I'm an online vendor, and each stream item is a sale(with an amount)<br>
> Query: return gross revenue so far
> Simple Algrithm: keep a counter

## Approximate Counting
> Maintain a counter `n` subject to 3 ops:
```cpp
init(){         n=0;        }
increase(){     n=n+1;      }
query(){        return n;   }
```
> Naive: if ```n``` 's range is $\{1,\dots，r \}$, then we need $log_2(r)$ bits of memory

We can settle for knowing ```n``` not exactly, but up to some approximation factor, i.e.
$$
n \le \hat n \le \alpha n
$$
in principle, could then use less memory by focusing on<br>
$$
\alpha^0,\alpha^1,\alpha^2, \cdots,\alpha^{\log_{\alpha}(r)}
$$
Naturally, we only have to stroe the exponents, so I only use $log_2(log_{\alpha}(r))$ bits.<br>
But how can we know which segment $n$ is located at?
(We cannot have another complete counter)

### Morris's Algo:
- stores $X$
- `init()`     $ X \leftarrow 0$
- `incr()` $ X \leftarrow X+1 \text{ with Pr} = \dfrac {1}{2^X}$
- `query()` `return` $ 2^X-1$


> **Claim:**<br>
> $E[2^X]=n+1$

<br>

**Pf**: <br>Let's denote $ X_i := \text{Value of }X \text{  after the first } i \text{ increase} $
**Induction**:
- Base case: $n=0$，$2^0-1$=0
- Inductive step:
$$
E[2^{X_{n+1}}] = \sum_{j=0}^{\infty} \Pr (X_n =j) \cdot E[2^{X_{n+1}}| X_n =j] =\sum_{j=0}^{\infty} \Pr (X_n =j) \cdot [\dfrac{1}{2^j} \cdot 2^{j+1} +(1-\dfrac{1}{2^j} ) \cdot 2^j ] \\= \sum_{j=0}^{\infty} \Pr (X_n =j) +\sum_{j=0}^{\infty} \Pr (X_n =j) \cdot 2^{j} =  1+ E[2^{X_n}]
$$

We care about $ Y := 2^{X} $
We know $E[Y] = n+1 $<br>
$Var[Y]=E[{Y^2}]- (E[Y])^2 $
> **Claim**:<br>
> $E[2^{2X}]= \dfrac{3}{2} \cdot n^2 + \dfrac{3}{2} \cdot n + 1 $


`Fact 1`<br>
If $A,B$ independent, then $Var[A+B]=Var[A]+Var[B]$<br>
`Fact 2`<br>$
Var[aB]=a^2 Var[B]$



## Problem 2: Distinct Elements
> Input a stream of items,$i_1,\cdots,i_n \in \{1,\cdots,n\} $ ; return $|\{1,\cdots,n$|$



### HyperLogLog
### bit vectore
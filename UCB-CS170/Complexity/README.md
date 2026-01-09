
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



## Intractability

Introduce intractability by reductions.

---

## Decision and Search — 0/1 strings and binary relation `R`

We encode all inputs and witnesses as binary strings. Let $\{0,1\}^*$ denote all finite binary strings.
> so $\{0,1\}^*$ contains infinite elements

- Instance: $x\in\{0,1\}^*$.
- Witness: $w\in\{0,1\}^*$.
- Relation: $R\subseteq\{0,1\}^*\times\{0,1\}^*$.

We say the language defined by $R$ is

$$
L_R := \{x\in\{0,1\}^* \mid \exists w\in\{0,1\}^*\text{ with }(x,w)\in R\}.
$$

Note: $\{0,1\}^*$ is infinite, so $L_R$ may be infinite as well.

`decide_R(x)`: the decision problem for $R$ returns `yes` iff $x\in L_R$, i.e.

\[
	ext{decide\_R}(x)=\begin{cases}
  	ext{yes} & \text{if }\exists w\; (x,w)\in R,\\
  	ext{no} & \text{otherwise.}
\end{cases}
\]

`search_R(x)`: the search problem for $R$ returns a witness $w$ with $(x,w)\in R$, or `no solution` if none exists.


Example: Network Flow in the $R$-framework

- Encode a directed graph with capacities and designated source/sink set $(G,S,T)$ as a binary string $x\in\{0,1\}^*$.
- Let witness $w$ encode a flow function $f$ on the edges of $G$ (using a finite binary representation of each flow value).
- Define the relation
  $$
  R:=\{(x,f)\;|\;\text{$f$ is a maximum $S$-$T$ flow in the graph encoded by }x\}\,.
  $$
- Equivalently, for input $x$ that decodes to $(G,S,T)$ and witness $w$ that decodes to flow $f$, write the predicate
  $$
  R(x,w) \iff \text{$f$ is a valid flow on $G$ and }\forall\,f'\;\big(\text{$f'$ is a valid }S\text{-}T\text{ flow}\;\Rightarrow\;\mathrm{value}(f')\le\mathrm{value}(f)\big).
  $$
- Under this definition, `decide_R(x)` answers whether there exists a maximum flow (i.e. whether $G$ admits any flow), and `search_R(x)` returns an encoding of a maximum flow $f$ when one exists.

 - It is self-evident that there always exists a maximum flow, and the definition of $R$ actually implies `search_R(x)`. In other words, the binary relation models the search problem.

<p>
Focus on (binary) relations that are efficiently computable.
<p>

$$
\exists\text{ verification algorithm }V_R\text{ s.t. }V_R(x,w)=R(x,w)\text{ and }\mathrm{time}(V_R)=\mathrm{poly}(|x|).
$$

> $V_R$ is a function that returns $1$ or $0$ to represent whether $R(x,w)$ holds.<br>
> If such $V_R$ exists, $R$ is efficiently computable.

- Observation:
\[
	ext{if } R \text{ is efficiently computable, then } \text{decide\_R} \text{ can be solved in } 2^{\mathrm{poly}(|x|)}.
\]

## Definitions of P and NP

The classes can be stated in the relation framework as follows:

$$
P = \{R \;|\; \text{the decision problem }\text{decide\_R}\text{ can be solved in polynomial time}\}
$$

$$
NP = \{R \;|\; \exists\text{ a polynomial-time verifier }V_R\text{ for }R\}.
$$

***


<br>



## Intractability

Introduce intractability by reductions.

---

## Decision and Search — 0/1 strings and binary relation `R`

We encode all inputs and witnesses as binary strings. Let $\{0,1\}^*$ denote all finite binary strings.
> so $\{0,1\}^*$ contains infinite elements

- Instance: $x\in\{0,1\}^*$.
- Witness: $w\in\{0,1\}^*$.
- Relation: $R\subseteq\{0,1\}^*\times\{0,1\}^*$.

We say the language (a subset of $\{0,1\}$) defined by $R$ is
$$

L_R:=\{x\in\{0,1\}^*\;|\;\exists w\in\{0,1\}^*\text{ with }(x,w)\in R\}.
$$
> so  $\ L_R$ may be infinite too

`decide_R(x)`: the decision problem for $R$ returns `yes` iff $x\in L_R$, i.e.
$$
	ext{decide\_R}(x)=\begin{cases} \text{yes} & \text{if }\exists w\; (x,w)\in R,\\ \text{no} & \text{otherwise.} \end{cases}
$$

`search_R(x)`: the search problem for $R$ returns a witness $w$ with $(x,w)\in R$, or `no solution` if none exists.

Relationship between decision and search (with 0/1 encoding)

- If you have `search_R(x)` then `decide_R(x)` is immediate: return `yes` when a witness is produced, `no` otherwise.
- Conversely, given an oracle for `decide_R`, we can recover a witness by querying whether there exists a witness with a specified prefix. Define for a binary prefix $p\in\{0,1\}^*$ the predicate
  $$
  \exists\,w\text{ with }\mathrm{pref}(w,p)\text{ and }(x,w)\in R
  $$
  (i.e. there exists $w$ that starts with prefix $p$ and satisfies $(x,w)\in R$). This predicate is itself a decision problem on the pair $(x,p)$ and can be answered by the `decide_R` oracle after suitable encoding of $(x,p)$ into a single binary string.

Bit-by-bit reconstruction algorithm (uses `decide_R` as an oracle)

1. Query `decide_R(x)`. If `no`, return `no solution`.
2. Let $m$ be an upper bound on witness length (polynomial in $|x|$). Initialize $p:=\varepsilon$ (empty prefix).
3. For $i=1\ldots m$:
   - Query the oracle for the predicate "there exists $w$ with prefix $p0$ and $(x,w)\in R$". If `yes`, set $p:=p0$; otherwise set $p:=p1$.
4. When a full-length witness is recovered (or once the verifier encoded by $R$ accepts $p$ as a complete witness), return $w:=p$.

Each iteration asks one (or a constant number of) oracle queries; since $m=\mathrm{poly}(|x|)$ the total number of `decide_R` calls is polynomial.

Example: 3-SAT in the $R$-framework

- Encode a 3-CNF formula $\phi$ as a binary string $x\in\{0,1\}^*$.
- Let witness $w\in\{0,1\}^n$ encode a truth assignment to the $n$ variables.
- Define
  $$
  R:=\{(x,w)\;|\;\text{$w$ is a satisfying assignment for the formula encoded by }x\}\,.
  $$
- Then `decide_R(x)` answers satisfiability and `search_R(x)` returns a satisfying assignment when one exists.

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

- It is self-evident that there always exists a maxminum flow, and the definition of $R$ actually implies `search_R(x)`. In other words, the binary relation models the search problem.

(Network-flow example inserted before the 3-SAT example.)

Conclusion

Using binary-string encodings and a binary relation $R$ cleanly captures both decision and search formulations: `decide_R` answers existence of a $w$ with $(x,w)\in R$, and `search_R` can be implemented by polynomially many calls to `decide_R` via prefix queries on witnesses.
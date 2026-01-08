
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
> pf by induction on |S|
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

> Karp,  Dinic,  Lee Sidford *O(m sqrt(n) log u)*



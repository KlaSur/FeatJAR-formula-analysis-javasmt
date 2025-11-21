package de.featjar.analysis.javasmt.computation;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.Formula;

import de.featjar.analysis.javasmt.solver.JavaSMTFormula;
import de.featjar.analysis.javasmt.solver.JavaSMTSolver;
import de.featjar.analysis.javasmt.solver.FormulaToJavaSMT.VariableReference;
import de.featjar.base.computation.Computations;
import de.featjar.base.computation.ComputeConstant;
import de.featjar.base.computation.Dependency;
import de.featjar.base.computation.IComputation;
import de.featjar.base.computation.Progress;
import de.featjar.base.data.Result;
import de.featjar.formula.assignment.BooleanAssignment;
import de.featjar.formula.structure.term.value.Variable;


/*
 * Copyright (C) 2025 FeatJAR-Development-Team
 *
 * This file is part of FeatJAR-formula-analysis-javasmt.
 *
 * formula-analysis-javasmt is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * formula-analysis-javasmt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with formula-analysis-javasmt. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-formula-analysis-javasmt> for further information.
 */

package de.featjar.analysis.javasmt.computation;

import java.util.Arrays;
import java.util.List;

import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;

import de.featjar.analysis.javasmt.solver.JavaSMTFormula;
import de.featjar.analysis.javasmt.solver.JavaSMTSolver;
import de.featjar.base.computation.IComputation;
import de.featjar.base.computation.Progress;
import de.featjar.base.data.Result;

/**
 * Enumerates valid solutions to a formula.
 *
 * @author Sebastian Krieter
 * @author Klara Surmeier
 */
public class ComputeSolutionEnumeration extends AJavaSMTAnalysis<List<List<BooleanFormula>>> {

    public ComputeSolutionEnumeration(IComputation<? extends JavaSMTFormula> formula) {
        super(formula);
    }

    @Override
    public Result<List<List<BooleanFormula>>> compute(List<Object> dependencyList, Progress progress) {
        JavaSMTSolver solver = initializeSolver(dependencyList);
    	
   	    // List<Solvers> compatibleSolvers = Arrays.asList(Solvers.MATHSAT5, Solvers.SMTINTERPOL);
        
        // Solvers solverName = solver.getSolverFormula().getSolverName();
        // if (!(compatibleSolvers.contains(solverName))) {
        //	return Result.empty(new UnsupportedOperationException(solverName + " does not support ComputeSolutionEnumeration."));
        // }
        
        return solver.enumerateSolutions();
    }
}

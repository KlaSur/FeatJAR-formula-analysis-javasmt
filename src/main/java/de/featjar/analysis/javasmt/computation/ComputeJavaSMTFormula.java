/*
 * Copyright (C) 2025 FeatJAR-Development-Team
 *
 * This file is part of FeatJAR-formula.
 *
 * formula is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * formula is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with formula. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-formula> for further information.
 */
package de.featjar.analysis.javasmt.computation;

import java.util.List;

import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.SolverContextFactory;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.SolverContext;

import de.featjar.analysis.javasmt.solver.JavaSMTFormula;
import de.featjar.base.FeatJAR;
import de.featjar.base.computation.AComputation;
import de.featjar.base.computation.Computations;
import de.featjar.base.computation.Dependency;
import de.featjar.base.computation.IComputation;
import de.featjar.base.computation.Progress;
import de.featjar.base.data.Result;
import de.featjar.formula.VariableMap;
import de.featjar.formula.assignment.ComputeBooleanClauseList;
import de.featjar.formula.structure.IExpression;
import de.featjar.formula.structure.IFormula;

/**
 * Transforms a formula into a {@link JavaSMTFormula}.
 *
 * @author Sebastian Krieter
 */
public class ComputeJavaSMTFormula extends AComputation<JavaSMTFormula> {
	public static final Dependency<IFormula> FORMULA = Dependency.newDependency(IFormula.class);
	public static final Dependency<Solvers> SOLVER = Dependency.newDependency(Solvers.class);

	    public ComputeJavaSMTFormula(IComputation<? extends IFormula> formula) {
	        super(formula, Computations.of(""));
	    }

	    protected ComputeJavaSMTFormula(ComputeBooleanClauseList other) {
	        super(other);
	    }
	    
	    @Override
	    public Result<JavaSMTFormula> compute(List<Object> dependencyList, Progress progress) {
	        IFormula vp = (IFormula) FORMULA.get(dependencyList);
	        Solvers solver = SOLVER.get(dependencyList);
	        
//	        Solvers solver = null;
//	        switch (solverType) {
//	        	case "MATHSAT5":
//	        		solver = Solvers.MATHSAT5;
//	        		break;
//	        	case "SMTINTERPOL":
//	        		solver = Solvers.SMTINTERPOL;
//	        		break;
//	        	case "Z3":
//	        		solver = Solvers.Z3;
//	        		break;
//	        	case "PRINCESS":
//	        		solver = Solvers.PRINCESS;
//	        		break;
//	        	case "BOOLECTOR":
//	        		solver = Solvers.BOOLECTOR;
//	        		break;
//	        	case "CVC4":
//	        		solver = Solvers.CVC4;
//	        		break;
//	        	case "YICES2":
//	        		solver = Solvers.YICES2;
//	        		break;
//	        }
	        
	        VariableMap variableMap = new VariableMap(vp);
	        
	        JavaSMTFormula formula = null;
	        SolverContext context;
	        
	        try {
	            final Configuration config = Configuration.defaultConfiguration();
	           
	            final LogManager logManager = BasicLogManager.create(config);
	            final ShutdownManager shutdownManager = ShutdownManager.create();
	            context =
	                    SolverContextFactory.createSolverContext(config, logManager, shutdownManager.getNotifier(), solver);
	           
	            formula = new JavaSMTFormula(context, vp, variableMap, solver);
	           
	      
	        } catch (final InvalidConfigurationException e) {
	            FeatJAR.log().error(e);
	        }
	        
	        return Result.of(formula);
	    }
	    
	    
}
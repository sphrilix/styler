/*
 * Portions copyright (c) 1998-1999, James Clark : see copyingjc.txt for
 * license details
 * Portions copyright (c) 2002, Bill Lindsey : see copying.txt for license
 * details
 * 
 * Portions copyright (c) 2009-2011 TIBCO Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.genxdm.processor.xpath.v10.expressions;

import org.genxdm.Model;
import org.genxdm.nodes.TraversingInformer;
import org.genxdm.processor.xpath.v10.variants.BooleanVariant;
import org.genxdm.xpath.v10.BooleanExpr;
import org.genxdm.xpath.v10.Converter;
import org.genxdm.xpath.v10.TraverserDynamicContext;
import org.genxdm.xpath.v10.TraverserVariant;
import org.genxdm.xpath.v10.NodeDynamicContext;
import org.genxdm.xpath.v10.StaticContext;
import org.genxdm.xpath.v10.StringExpr;
import org.genxdm.xpath.v10.NodeVariant;
import org.genxdm.xpath.v10.VariantExpr;

/**
 * An expression which is a boolean, but depending on its use, may be converted to a Number, String or Object on its use, may be converted to a Number, String or Object
 */
public abstract class ConvertibleBooleanExpr
    extends ConvertibleExprImpl
    implements BooleanExpr
{

	public StringExpr makeStringExpr(final StaticContext statEnv)
	{
		return new ConvertibleStringExpr()
		{
            @Override
			public <N> String stringFunction(Model<N> model, final N node, final NodeDynamicContext<N> context) {
				return Converter.toString(ConvertibleBooleanExpr.this.booleanFunction(model, node, context));
			}

            @Override
            public String stringFunction(TraversingInformer contextNode, TraverserDynamicContext dynEnv) {
                return Converter.toString(ConvertibleBooleanExpr.this.booleanFunction(contextNode, dynEnv));
            }
		};
	}

	@Override
	public ConvertibleNumberExpr makeNumberExpr(final StaticContext statEnv)
	{
		return new ConvertibleNumberExpr()
		{
            @Override
			public <N> double numberFunction(Model<N> model, final N contextNode, final NodeDynamicContext<N> context) {
				return Converter.toNumber(ConvertibleBooleanExpr.this.booleanFunction(model, contextNode, context));
			}

            @Override
            public double numberFunction(TraversingInformer contextNode, TraverserDynamicContext dynEnv) {
                return Converter.toNumber(ConvertibleBooleanExpr.this.booleanFunction(contextNode, dynEnv));
            }
		};
	}

	public BooleanExpr makeBooleanExpr(final StaticContext statEnv)
	{
		return this;
	}

	public VariantExpr makeVariantExpr(final StaticContext statEnv)
	{
		return new ConvertibleVariantExpr()
		{
            @Override
			public <N> NodeVariant<N> evaluateAsVariant(Model<N> model, final N contextNode, final NodeDynamicContext<N> dynEnv) {
				return new BooleanVariant<N>(ConvertibleBooleanExpr.this.booleanFunction(model, contextNode, dynEnv));
			}

            @Override
            public TraverserVariant evaluateAsVariant(TraversingInformer contextNode, TraverserDynamicContext dynEnv) {
                return new BooleanVariant<Object>(ConvertibleBooleanExpr.this.booleanFunction(contextNode, dynEnv));
            }
		};
	}
}

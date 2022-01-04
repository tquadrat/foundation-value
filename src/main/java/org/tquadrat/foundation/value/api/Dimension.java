/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.value.api;

import static org.apiguardian.api.API.Status.STABLE;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  The implementations of this interface are used to give (numerical) values a
 *  dimension and a measuring unit. So {@code 3.1415} is just a number, but it
 *  could also stand for a length ({@code 3.1415&nbsp;m}), a time
 *  ({@code 3.1415&nbsp;s}) or even the fuel consumption of a car
 *  ({@code 3.1415&nbsp;l/100&nbsp;km}). Depending on the dimension, different
 *  values could be equal:
 *  <pre><code>1.0&nbsp;m == 100.0&nbsp;cm</code></pre>
 *  and should be treated as such.<br>
 *  <br>This interface should be implemented as
 *  {@link java.lang.Enum enum}s, where the enum values are the units,
 *  and as such they should provide a method to retrieve an {@code enum} value
 *  (a unit) by the respective
 *  {@linkplain #unitSymbol() symbole}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Dimension.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Dimension.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public interface Dimension extends Serializable
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  Message: Unknown Unit.
     */
    public static final String MSG_UnknownUnit = "Unknown unit: %s";

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the base unit.<br>
     *  <br>E.g. for length, the base unit would be Meter (m), for mass, it is
     *  Kilogram (kg), and so on.
     *
     *  @param  <D> The implementing class for the interface.
     *  @return The base unit.
     */
    public <D extends Dimension> D baseUnit();

    /**
     *  {@inheritDoc}<br>
     *  <br>Dimensions are equal when they are identical; therefore they
     *  should be implemented as <i>Multitons</i> (constants of the
     *  implementing class, without the option to create additional instances
     *  on runtime).
     *
     *  @see Object#equals(Object)
     */
    @Override
    public boolean equals( Object o );

    /**
     *  Returns the factor that is used to convert a value from this unit
     *  to the base unit.<br>
     *  <br>For length, if you have to convert a
     *  Centimeter value to Meter, you will divide that by 100 or multiply it
     *  with a <i>factor</i> of 0.01.<br>
     *  <br>For the base unit, the factor is 1.0.
     *
     *  @return The factor.
     *
     *  @see #baseUnit()
     */
    public BigDecimal factor();

    /**
     *  Returns the default precision for this unit that is used when the
     *  respective value is converted to a String.
     *
     *  @return The mantissa length for a value with this unit.
     */
    public default int getPrecision() { return 1; }

    /**
     *  Returns the internal name of the dimension.
     *
     *  @return The internal name.
     */
    public String name();

    /**
     *  {@inheritDoc}
     *
     *  @note {@code toString()} should be implemented to return the result of
     *      {@link #unitSymbol()}
     *      instead of
     *      {@link Enum#name()}, as it would be the default for {@code enum}s.
     */
    @Override
    public String toString();

    /**
     *  Returns the unit symbol for the dimension as a single line string.<br>
     *  <br>For a length, this would be &quot;m&quot;, for a speed
     *  &quot;km/h&quot;, and for an acceleration, it could be
     *  &quot;m/(s^2)&quot;.
     *
     *  @return The unit.
     */
    public String unitSymbol();
}
//  interface Dimension

/*
 *  End of File
 */
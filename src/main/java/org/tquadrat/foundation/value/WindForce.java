/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
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

package org.tquadrat.foundation.value;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.i18n.I18nUtil.retrieveText;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.DebugOutput.ifDebug;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.value.Speed.METER_PER_SECOND;
import static org.tquadrat.foundation.value.internal.Tools.BASE_BUNDLE_NAME;
import static org.tquadrat.foundation.value.internal.Tools.retrieveName;

import java.util.EnumSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.i18n.Text;
import org.tquadrat.foundation.i18n.Translation;
import org.tquadrat.foundation.lang.Lazy;
import org.tquadrat.foundation.util.RangeMap;

/**
 *  The wind force according to the Beaufort table.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: WindForce.java 1073 2023-10-01 11:08:51Z tquadrat $
 *  @since 0.0.4
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: WindForce.java 1073 2023-10-01 11:08:51Z tquadrat $" )
@API( status = STABLE, since = "0.0.4" )
public enum WindForce
{
        /*------------------*\
    ====** Enum Declaration **=================================================
        \*------------------*/
    /**
     *  Wind force 0 Bft.
     */
    @Text
    (
        description = "The name for 0 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Windstille" ),
            @Translation( language = "en", text = "Calm" )
        }
    )
    BFT0( 0, "0.2" ),

    /**
     *  Wind force 1 Bft.
     */
    @Text
    (
        description = "The name for 1 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Leiser Zug" ),
            @Translation( language = "en", text = "Light Air" )
        }
    )
    BFT1( 1, "1.5" ),

    /**
     *  Wind force 2 Bft.
     */
    @Text
    (
        description = "The name for 2 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Leichte Brise" ),
            @Translation( language = "en", text = "Light Breeze" )
        }
    )
    BFT2( 2, "3.3" ),

    /**
     *  Wind force 3 Bft.
     */
    @Text
    (
        description = "The name for 3 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Schwache Brise" ),
            @Translation( language = "en", text = "Gentle Breeze" )
        }
    )
    BFT3( 3, "5.4" ),

    /**
     *  Wind force 4 Bft.
     */
    @Text
    (
        description = "The name for 4 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Mäßige Brise" ),
            @Translation( language = "en", text = "Moderate Breeze" )
        }
    )
    BFT4( 4, "8.9" ),

    /**
     *  Wind force 5.
     */
    @Text
    (
        description = "The name for 5 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Frische Brise" ),
            @Translation( language = "en", text = "Fresh Breeze" )
        }
    )
    BFT5( 5, "11.0" ),

    /**
     *  Wind force 6.
     */
    @Text
    (
        description = "The name for 6 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Starker Wind" ),
            @Translation( language = "en", text = "Strong Breeze" )
        }
    )
    BFT6( 6, "14.0" ),

    /**
     *  Wind force 7.
     */
    @Text
    (
        description = "The name for 7 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Steifer Wind" ),
            @Translation( language = "en", text = "Moderate Gale" )
        }
    )
    BFT7( 7, "17.0" ),

    /**
     *  Wind force 8.
     */
    @Text
    (
        description = "The name for 8 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Stürmischer Wind" ),
            @Translation( language = "en", text = "Gale" )
        }
    )
    BFT8( 8, "21.0" ),

    /**
     *  Wind force 9.
     */
    @Text
    (
        description = "The name for 9 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Sturm" ),
            @Translation( language = "en", text = "Strong Gale" )
        }
    )
    BFT9( 9, "24.0" ),

    /**
     *  Wind force 10 Bft.
     */
    @Text
    (
        description = "The name for 10 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Schwerer Sturm" ),
            @Translation( language = "en_GB", text = "Whole Gale" ),
            @Translation( language = "en", text = "Storm" )
        }
    )
    BFT10( 10, "28.0" ),

    /**
     *  Wind force 11 Bft.
     */
    @Text
    (
        description = "The name for 11 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Orkanartiger Sturm" ),
            @Translation( language = "en_GB", text = "Storm" ),
            @Translation( language = "en", text = "Violent Storm" )
        }
    )
    BFT11( 11, "33.0" ),

    /**
     *  Wind force 12 Bft.
     */
    @Text
    (
        description = "The name for 12 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Orkan" ),
            @Translation( language = "en", text = "Hurricane" )
        }
    )
    BFT12( 12, "36.9" ),

    /**
     *  Wind force 13 Bft.; although it is not official, we added it to the
     *  list.
     */
    @Text
    (
        description = "The name for 13 Bft.",
        translations =
        {
            @Translation( language = "de", text = "Schwerer Orkan" ),
            @Translation( language = "en", text = "Heavy Hurricane" )
        }
    )
    BFT13( 13, EMPTY_STRING );

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The maximum wind speed in m/s.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    private final SpeedValue m_MaxSpeed;

    /**
     *  The numerical value for the wind force.
     */
    private final int m_Number;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The translation table.
     */
    private static final Lazy<RangeMap<WindForce>> m_TranslationTable;

    static
    {
        @SuppressWarnings( "OverlyLongLambda" )
        final var supplier = (Supplier<RangeMap<WindForce>>) () ->
        {
            final var retValue = RangeMap.of( BFT13, true );
            double maxSpeed;
            for( final var windForce : values() )
            {
                if( windForce == BFT13 ) continue;
                maxSpeed = windForce.getMaxSpeed().baseValue().doubleValue();
                retValue.addRange( maxSpeed, windForce );
            }

            //---* Done *------------------------------------------------------
            return retValue;
        };
        m_TranslationTable = Lazy.use( supplier );
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code WindForce} object.
     *
     *  @param  number  The numerical wind force.
     *  @param  maxSpeed    The maximum wind speed for this wind force in m/s.
     */
    private WindForce( final int number, final String maxSpeed )
    {
        m_Number = number;
        m_MaxSpeed = maxSpeed.isEmpty()
            ? new SpeedValue( METER_PER_SECOND, Double.POSITIVE_INFINITY )
            : new SpeedValue( METER_PER_SECOND, maxSpeed );
    }   //  WindForce()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the wind force for the given wind speed.
     *
     *  @param  speed   The wind speed in m/s.
     *  @return The wind force.
     */
    public static WindForce determineWindForce( final double speed )
    {
        return determineWindForce( METER_PER_SECOND, speed );
    }   //  determineWindForce()

    /**
     *  Returns the wind force for the given wind speed.
     *
     *  @param  unit    The unit for the speed value.
     *  @param  speed   The wind speed.
     *  @return The wind force.
     */
    public static WindForce determineWindForce( final Speed unit, final double speed )
    {
        return determineWindForce( new SpeedValue( requireNonNullArgument( unit, "unit" ), speed ) );
    }   //  determineWindForce()

    /**
     *  Returns the wind force for the given wind speed.
     *
     *  @param  speed   The wind speed.
     *  @return The wind force.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    public static WindForce determineWindForce( final SpeedValue speed )
    {
        return m_TranslationTable.get().get( requireNonNullArgument( speed, "speed" ).baseValue().doubleValue() );
    }   //  determineWindForce()

    /**
     *  Return an empty
     *  {@link Set}
     *  of {@code WindForce} instances.
     *
     *  @return An empty set.
     */
    public static Set<WindForce> emptySet() { return EnumSet.noneOf( WindForce.class ); }

    /**
     *  Returns maximum wind speed for this wind force.
     *
     *  @return The maximum speed.
     */
    public final SpeedValue getMaxSpeed() { return m_MaxSpeed; }

    /**
     *  Returns the wind force number.
     *
     *  @return The number.
     */
    public final int getNumber() { return m_Number; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final var retValue = retrieveName( this );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  Returns the String representation for this wind force for the given
     *  language.
     *
     *  @param  locale  The locale.
     *  @return The String representation.
     */
    public final String toString( final Locale locale )
    {
        String retValue;
        try
        {
            final var module = getClass().getModule();
            final var bundle = ResourceBundle.getBundle( BASE_BUNDLE_NAME, requireNonNullArgument( locale, "locale" ), module );
            retValue = retrieveText( bundle, this );
        }
        catch( final MissingResourceException e )
        {
            ifDebug( e );
            retValue = toString();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class WindForce

/*
 *  End of File
 */
/*
 * ============================================================================
 *  Copyright © 2002-2022 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.value.internal;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.i18n.I18nUtil.loadResourceBundle;
import static org.tquadrat.foundation.i18n.I18nUtil.retrieveText;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.value.api.DimensionedValue.MATH_CONTEXT;

import java.math.BigDecimal;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.i18n.BaseBundleName;

/**
 *  Internal utilities for the value module.
 *
 *  @version $Id: Tools.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: Tools.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class Tools
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/

        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The base class for the resource bundle.
     */
    @BaseBundleName
    public static final String BASE_BUNDLE_NAME = "org.tquadrat.foundation.value.Texts";

    /**
     *  {@summary 1/3}
     */
    public static final BigDecimal V1t3 = BigDecimal.ONE.divide( new BigDecimal( 3 ), MATH_CONTEXT );

    /**
     *  {@summary 5/9}
     */
    public static final BigDecimal V5t9 = new BigDecimal( 5 ).divide( new BigDecimal( 9 ), MATH_CONTEXT );

    /**
     *  {@summary 0.525}
     */
    public static final BigDecimal V0t525 = new BigDecimal( "0.525" );

    /**
     *  {@summary 0.8}
     */
    public static final BigDecimal V0p8 = new BigDecimal( "0.8" );

    /**
     *  {@summary 1.5}
     */
    public static final BigDecimal V1p5 = new BigDecimal( "1.5" );

    /**
     *  {@summary 7.5}
     */
    public static final BigDecimal V7p5 = new BigDecimal( "7.5" );

    /**
     *  {@summary 32}
     */
    public static final BigDecimal V32 = new BigDecimal( 32 );

    /**
     *  {@summary 100}
     */
    public static final BigDecimal V100 = new BigDecimal( 100 );

    /**
     *  {@summary 273.15}
     */
    public static final BigDecimal V273 = new BigDecimal( "273.15" );

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The resource bundle for this module.
     */
    private static final ResourceBundle m_ResourceBundle;

    static
    {
        final var module = Tools.class.getModule();
        try
        {
            m_ResourceBundle = loadResourceBundle( BASE_BUNDLE_NAME, module )
                .orElseThrow( () -> new MissingResourceException( "Cannot find ResourceBundle", BASE_BUNDLE_NAME, EMPTY_STRING ) );
        }
        catch( final MissingResourceException e )
        {
            throw new ExceptionInInitializerError( e );
        }
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     * No instance allowed for this class.
     */
    private Tools() { throw new PrivateConstructorForStaticClassCalledError( Tools.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the translation for the given enum name.
     *
     *  @param  <E> The type of the enum.
     *  @param  value   The enum value.
     *  @return The translation.
     */
    public static final <E extends Enum<?>> String retrieveName( final E value )
    {
        final var retValue = retrieveText( m_ResourceBundle, value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  retrieveName()
}
//  class Tools

/*
 *  End of File
 */
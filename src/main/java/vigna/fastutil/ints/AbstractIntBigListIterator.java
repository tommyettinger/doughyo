/* Generic definitions */
/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/*		 
 * Copyright (C) 2002-2015 Sebastiano Vigna
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package vigna.fastutil.ints;

import vigna.fastutil.BigListIterator;

import java.util.ListIterator;

/** An abstract class facilitating the creation of type-specific {@linkplain BigListIterator big-list iterators}.
 *
 * <p>This implementation provides (deprecated) implementations of {@link ListIterator#previousIndex()} and {@link ListIterator#nextIndex()} that just invoke the corresponding {@link BigListIterator}
 * methods.
 *
 * @see ListIterator
 * @see BigListIterator */
public abstract class AbstractIntBigListIterator extends AbstractIntBidirectionalIterator implements IntBigListIterator {
	protected AbstractIntBigListIterator() {}

	/** Delegates to the corresponding type-specific method. */
	public void set( Integer ok ) {
		set( ok.intValue() );
	}

	/** Delegates to the corresponding type-specific method. */
	public void add( Integer ok ) {
		add( ok.intValue() );
	}

	/** This method just throws an {@link UnsupportedOperationException}. */
	public void set( int k ) {
		throw new UnsupportedOperationException();
	}

	/** This method just throws an {@link UnsupportedOperationException}. */
	public void add( int k ) {
		throw new UnsupportedOperationException();
	}

	/** This method just iterates the type-specific version of {@code next()} for at most <code>n</code> times, stopping if {@link #hasNext()} becomes false. */
	public long skip( final long n ) {
		long i = n;
		while ( i-- != 0 && hasNext() )
			nextInt();
		return n - i - 1;
	}

	/** This method just iterates the type-specific version of {@link #previous()} for at most <code>n</code> times, stopping if {@link #hasPrevious()} becomes false. */
	public long back( final long n ) {
		long i = n;
		while ( i-- != 0 && hasPrevious() )
			previousInt();
		return n - i - 1;
	}
}

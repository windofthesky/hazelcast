/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.quorum.list;

import com.hazelcast.config.Config;
import com.hazelcast.core.IList;
import com.hazelcast.quorum.QuorumException;
import com.hazelcast.quorum.QuorumType;
import com.hazelcast.test.HazelcastParametersRunnerFactory;
import com.hazelcast.test.TestHazelcastInstanceFactory;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.util.Arrays.asList;

@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(HazelcastParametersRunnerFactory.class)
@Category({QuickTest.class})
public class ListReadQuorumTest extends AbstractListQuorumTest {

    @Parameterized.Parameter
    public static QuorumType quorumType;

    @Parameterized.Parameters(name = "quorumType:{0}")
    public static Iterable<Object[]> parameters() {
        return asList(new Object[][]{{QuorumType.READ}, {QuorumType.READ_WRITE}});
    }

    @BeforeClass
    public static void setUp() {
        initTestEnvironment(new Config(), new TestHazelcastInstanceFactory());
    }

    @AfterClass
    public static void tearDown() {
        shutdownTestEnvironment();
    }

    // size
    // isEmpty
    // contains
    // iterator
    // listIterator
    // containsAll(Collection<?> c);
    // iterator
    // toArray()
    // toArray(T[] a);
    // indexof
    // sub

    @Test
    public void containsOperation_successful_whenQuorumSize_met() {
        list(0).contains("foo");
    }

    @Test(expected = QuorumException.class)
    public void containsOperation_successful_whenQuorumSize_notMet() {
        list(3).contains("foo");
    }

    @Test
    public void containsAllOperation_successful_whenQuorumSize_met() {
        list(0).containsAll(asList("foo", "bar"));
    }

    @Test(expected = QuorumException.class)
    public void containsAllOperation_successful_whenQuorumSize_notMet() {
        list(3).containsAll(asList("foo", "bar"));
    }

    @Test
    public void isEmptyOperation_successful_whenQuorumSize_met() {
        list(0).isEmpty();
    }

    @Test(expected = QuorumException.class)
    public void isEmptyOperation_successful_whenQuorumSize_notMet() {
        list(3).isEmpty();
    }

    @Test
    public void sizeOperation_successful_whenQuorumSize_met() {
        list(0).size();
    }

    @Test(expected = QuorumException.class)
    public void sizeOperation_successful_whenQuorumSize_notMet() {
        list(3).size();
    }

    @Test
    public void getAllOperation_toArray_successful_whenQuorumSize_met() {
        list(0).toArray();
    }

    @Test(expected = QuorumException.class)
    public void getAllOperation_toArray_successful_whenQuorumSize_notMet() {
        list(3).toArray();
    }

    @Test
    public void getAllOperation_toArrayT_successful_whenQuorumSize_met() {
        list(0).toArray(new Object[]{});
    }

    @Test(expected = QuorumException.class)
    public void getAllOperation_toArrayT_successful_whenQuorumSize_notMet() {
        list(3).toArray(new Object[]{});
    }

    @Test
    public void getAllOperation_listIterator_successful_whenQuorumSize_met() {
        list(0).listIterator();
    }

    @Test(expected = QuorumException.class)
    public void getAllOperation_listIterator_successful_whenQuorumSize_notMet() {
        list(3).listIterator();
    }

    @Test
    public void getAllOperation_iterator_successful_whenQuorumSize_met() {
        list(0).iterator();
    }

    @Test(expected = QuorumException.class)
    public void getAllOperation_iterator_successful_whenQuorumSize_notMet() {
        list(3).iterator();
    }

    @Test
    public void lastIndexOfOperation_whenQuorumSize_met() {
        list(0).lastIndexOf("foo");
    }

    @Test(expected = QuorumException.class)
    public void lastIndexOfOperation_successful_whenQuorumSize_notMet() {
        list(3).lastIndexOf("foo");
    }

    @Test
    public void indexOfOperation_whenQuorumSize_met() {
        list(0).indexOf("foo");
    }

    @Test(expected = QuorumException.class)
    public void indexOfOperation_successful_whenQuorumSize_notMet() {
        list(3).indexOf("foo");
    }

    @Test
    public void getOperation_whenQuorumSize_met() {
        list(0).add("object");
        list(0).get(0);
    }

    @Test(expected = QuorumException.class)
    public void getOperation_successful_whenQuorumSize_notMet() {
        list(3).get(1);
    }

    @Test
    public void subOperation_whenQuorumSize_met() {
        list(0).add("object");
        list(0).subList(0, 1);
    }

    @Test(expected = QuorumException.class)
    public void subOperation_successful_whenQuorumSize_notMet() {
        list(3).subList(0, 1);
    }

    protected IList list(int index) {
        return list(index, quorumType);
    }
}

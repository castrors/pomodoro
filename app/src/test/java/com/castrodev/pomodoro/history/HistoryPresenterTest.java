package com.castrodev.pomodoro.history;

import android.test.mock.MockContext;

import com.castrodev.pomodoro.model.Pomodoro;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by rodrigocastro on 03/08/16.
 */
public class HistoryPresenterTest {

    @Mock
    HistoryContract.View historyContractView;

    MockContext mockContext = new MockContext();

    HistoryPresenter historyPresenter;

    @Before
    public void setupPresenter(){
        MockitoAnnotations.initMocks(this);

        historyPresenter = new HistoryPresenter(historyContractView, mockContext);
    }

    @Test
    public void shouldLoadHistoryListView(){
        historyPresenter.requestPomodoroHistory();
        verify(historyContractView).showPomodoroHistory(eq(new ArrayList<Pomodoro>(0)));
    }
}

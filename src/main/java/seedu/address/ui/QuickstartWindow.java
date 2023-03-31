package seedu.address.ui;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Controller for a QuickstartWindow.
 */
public class QuickstartWindow extends UiPart<Stage> {

    private static final String FXML = "QuickstartWindow.fxml";

    private boolean firstTimeFocus;

    @FXML
    private Pagination quickPagination;

    /**
     * Creates a new QuickstartWindow.
     */
    public QuickstartWindow() {
        this(new Stage());
    }

    /**
     * Creates a new QuickstartWindow, with the appropriate firstTime info.
     *
     * @param root Stage to use as the root of the QuickstartWindow.
     */
    public QuickstartWindow(Stage root) {
        super(FXML, root);
        ImageView initImageView = new ImageView();
        initImageView.setPickOnBounds(true);
        initImageView.setPreserveRatio(true);
        initImageView.setFitHeight(466.0);
        initImageView.setFitWidth(670.0);
        ImageView quickImageView = initImageView;
        quickImageView.setImage(pageImageSet(0));
        quickPagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                quickImageView.setImage(pageImageSet(pageIndex));
                return quickImageView;
            }
        });
        if (this.checkFirstTime()) {
            this.show();
            this.firstTimeFocus = true;
        } else {
            this.firstTimeFocus = false;
        }
    }

    /**
     * returns firstTimeFocus, which focuses quickstartwindow upon knowing
     * that a user is a first-time user (i.e., one that has never used this
     * application before)
     */
    public boolean getFirstTimeFocus() {
        return this.firstTimeFocus;
    }

    /**
     * checks if user is a first time user by presence of text file
     * called firstTimeUser.txt.
     */
    public static boolean checkFirstTime() {
        boolean returnBool = false;
        try {
            String firstTimeIndicatorTextDir = System.getProperty("user.dir") + "/firstTimeUser.txt";
            File fileToCheckFirstTime = new File(firstTimeIndicatorTextDir);
            if (!fileToCheckFirstTime.exists()) {
                returnBool = true;
                fileToCheckFirstTime.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnBool;
    }

    /**
     * Returns an image according to the current page number in Pagination.
     *
     * @param pageNum page number (index 0) being passed by the quickPaginator.
     */
    public static Image giveImage(int pageNum) {
        switch (pageNum) {
        case 0:
            return QuickstartImages.QUICKSTART_FIRSTPAGE;
        case 1:
            return QuickstartImages.QUICKSTART_SECONDPAGE;
        case 2:
            return QuickstartImages.QUICKSTART_THIRDPAGE;
        case 3:
            return QuickstartImages.QUICKSTART_FOURTHPAGE;
        case 4:
            return QuickstartImages.QUICKSTART_FIFTHPAGE;
        case 5:
            return QuickstartImages.QUICKSTART_SIXTHPAGE;
        case 6:
            return QuickstartImages.QUICKSTART_SEVENTHPAGE;
        case 7:
            return QuickstartImages.QUICKSTART_EIGHTHPAGE;
        case 8:
            return QuickstartImages.QUICKSTART_NINTHPAGE;
        case 9:
            return QuickstartImages.QUICKSTART_TENTHPAGE;
        case 10:
            return QuickstartImages.QUICKSTART_ELEVENTHPAGE;
        case 11:
            return QuickstartImages.QUICKSTART_TWELFTHPAGE;
        case 12:
            return QuickstartImages.QUICKSTART_THIRTEENTHPAGE;
        case 13:
            return QuickstartImages.QUICKSTART_FOURTEENTHPAGE;
        case 14:
            return QuickstartImages.QUICKSTART_FIFTEENTHPAGE;

        default:
            return QuickstartImages.QUICKSTART_FIRSTPAGE;
        }
    }

    public Image pageImageSet(Integer pageIndex) {
        return giveImage(pageIndex);
    }

    /**
     * Shows the QuickstartWindow.
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the QuickstartWindow is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the QuickstartWindow.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the QuickstartWindow.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

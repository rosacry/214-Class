public class TreeNode {
  private TreeNode left;
  private TreeNode mid;
  private TreeNode right;
  private TreeNode[] childNodes = new TreeNode[] { left, mid, right }; // get back to this when you do ec
  private String label;
  private String message;
  private String prompt;

  public TreeNode(String nodeLabel) {
    this.label = nodeLabel;
    left = mid = right = null;
  }

  public TreeNode(String nodeLabel, String nodePrompt, String nodeMessage) {
    this.label = nodeLabel;
    this.prompt = nodePrompt;
    this.message = nodeMessage;
    left = mid = right = null;
  }

  public TreeNode getLeft() {
    return left;
  }

  public TreeNode getMid() {
    return mid;
  }

  public TreeNode getRight() {
    return right;
  }

  public String getLabel() {
    return label;
  }

  public String getMessage() {
    return message;
  }

  public String getPrompt() {
    return prompt;
  }

  public String toString() {
    return "\n" + getLabel() + "\n" + getPrompt() + "\n" + getMessage();
  }

  public boolean isLeaf() {
    if (left == null && mid == null && right == null)
      return true;
    return false;
  }

  public boolean isFull() {
    if (left != null && mid != null && right != null)
      return true;
    return false;
  }

  public int count() {
    if (left == null)
      return 0;
    if (left != null && mid == null)
      return 1;
    if (mid != null && right == null)
      return 2;
    else
      return 3;
  }

  public void setLeft(TreeNode newLeft) {
    left = newLeft;
  }

  public void setMid(TreeNode newMid) {
    mid = newMid;
  }

  public void setRight(TreeNode newRight) {
    right = newRight;
  }

  public void setLabel(String newLabel) {
    label = newLabel;
  }

  public void setMessage(String newMessage) {
    message = newMessage;
  }

  public void setPrompt(String newPrompt) {
    prompt = newPrompt;
  }
}
